package com.thomas.bateau.coins.searchActivity;

import android.app.Fragment;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.thomas.bateau.AdvancedSearchFragment;
import com.thomas.bateau.BateauApplication;
import com.thomas.bateau.Character;
import com.thomas.bateau.FileManager;
import com.thomas.bateau.R;
import com.thomas.bateau.TypeUtilisateurs;
import com.thomas.bateau.coins.ItemListView;
import com.thomas.bateau.coins.ItemListViewAdapter;
import com.thomas.bateau.coins.kitesurfer.CoinKitesurferFactory;
import com.thomas.bateau.coins.pecheur.CoinPecheurFactory;
import com.thomas.bateau.coins.plongeur.CoinPlongeurFactory;
import com.thomas.bateau.coins.scientifique.CoinScientifiqueFactory;
import com.thomas.bateau.coins.searchActivity.result.ResultActivity;
import com.thomas.bateau.coins.skippeur.CoinSkippeurFactory;
import com.thomas.bateau.spot.CommonSpotActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchFragment extends Fragment {


    Button buttonResearch;
    AdvancedSearchFragment advancedResearch;
    EditText text;
    List<JSONObject> elements;
    LinkedList<ItemListView> items;
    ListView listView;
    boolean advanced = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.search_fragment, container, false);
        v.findViewById(R.id.result_return).setOnClickListener(click -> getActivity().finish());
        initListJson();
        initSearchButton(v);
        advancedResearch = new AdvancedSearchFragment();
        buttonResearch = v.findViewById(R.id.show_more_button);
        buttonResearch.setText("Recherche avancé ");
        buttonResearch.setOnClickListener(click -> swap());
        text = v.findViewById(R.id.edit_button_search);
        try {
            initListView(v);
        }catch ( JSONException ignored){}
        return v;
    }
    void initSearchButton(View v)
    {
        v.findViewById(R.id.search_button).setOnClickListener(click->{
            items.clear();
            for (JSONObject jsonObject :filterJsonObjects(elements,text.getText().toString(),"fish","description")) {
                try { items.add(createItemListView(jsonObject));
                } catch (JSONException ignored) {}
            }
            ItemListViewAdapter adapter = new ItemListViewAdapter(getActivity().getApplicationContext(), R.layout.search_activity, items);
            listView.setAdapter(adapter);

        } );
    }
    void initListJson()
    {
        ContextWrapper contextWrapper = new ContextWrapper(getActivity().getApplicationContext());
        try {
            elements = filterJsonObjects(FileManager.loadFile(contextWrapper.getDir("jsonDir", Context.MODE_PRIVATE).toString())
                    ,  String.valueOf(BateauApplication.typeUtilisateurs.ordinal()),"type");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    void initListView(View v) throws  JSONException {
        ItemListViewAdapter adapter = new ItemListViewAdapter(getActivity().getApplicationContext(), R.layout.search_activity, fillListView());
        listView = (ListView) v.findViewById(R.id.result_activity_listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(((parent, view, position, id) -> launchResearchSelected()));
    }

    LinkedList<ItemListView> fillListView() throws JSONException {
        items = new LinkedList<ItemListView>();
        for (JSONObject jsonObject :elements) items.add(createItemListView(jsonObject));
        return items;
    }



    private ItemListView createItemListView(JSONObject jsonObject) throws JSONException
    {
        return new ItemListView(setTitleItemListView(jsonObject), R.drawable.canne_icon, jsonObject.get("description").toString());
    }


    List<JSONObject> filterJsonObjects(List<JSONObject> jsonObjects, String reference, String... filters) {
        return jsonObjects.stream().filter(jsonObject -> Arrays.stream(filters).anyMatch(filter-> {
                    try { return jsonObject.get(filter).equals(reference);
                    } catch (JSONException jsonException) { return false; } })
        ).collect(Collectors.toList());

    }

    void launchResearchSelected() {
            startActivity(new Intent(getContext(), ResultActivity.class));
    }

    private String setTitleItemListView(JSONObject json ) throws JSONException {
        if(BateauApplication.typeUtilisateurs.equals(TypeUtilisateurs.SKIPPER) ||
                BateauApplication.typeUtilisateurs.equals(TypeUtilisateurs.KITTER) )
            return "Coin sympas";
        else return json.get("fish").toString();

    }
    private void swap() {
        if (advanced) getFragmentManager().beginTransaction().remove(advancedResearch).commit();
        else getFragmentManager().beginTransaction().replace(R.id.result_activity_linearLayout, advancedResearch).commit();
        advanced = !advanced;
    }



}
