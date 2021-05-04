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
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchFragment extends Fragment {


    Button buttonResearch;
    AdvancedSearchFragment advancedResearch;
    boolean advanced = false;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.search_fragment, container, false);
        v.findViewById(R.id.result_return).setOnClickListener(click -> getActivity().finish());
        advancedResearch = new AdvancedSearchFragment();
        buttonResearch = v.findViewById(R.id.show_more_button);
        buttonResearch.setText("Recherche avancé ");
        buttonResearch.setOnClickListener(click -> swap());
        try {
            initListView(v);
        }catch (IOException | JSONException ignored){}


        return v;
    }

    void initListView(View v) throws IOException, JSONException {
        ItemListViewAdapter adapter = new ItemListViewAdapter(getActivity().getApplicationContext(), R.layout.search_activity, fillListView());
        ListView list = (ListView) v.findViewById(R.id.result_activity_listView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(((parent, view, position, id) -> launchResearchSelected()));
    }

    LinkedList<ItemListView> fillListView() throws IOException,JSONException {
        LinkedList<ItemListView> items = new LinkedList<ItemListView>();
        ContextWrapper contextWrapper = new ContextWrapper(getActivity().getApplicationContext());
        List<JSONObject>jsonObjects = FileManager.loadFile(contextWrapper.getDir("jsonDir", Context.MODE_PRIVATE).toString());

        for (JSONObject jsonObject : filterJsonObjects(jsonObjects, "type", String.valueOf(BateauApplication.typeUtilisateurs.ordinal())))
            items.add(createItemListView(jsonObject));

        return items;
    }

    private ItemListView createItemListView(JSONObject jsonObject) throws JSONException
    {
        return new ItemListView(setTitleItemListView(jsonObject), R.drawable.canne_icon, jsonObject.get("description").toString());
    }
    List<JSONObject> filterJsonObjects(List<JSONObject> jsonObjects, String filter, String reference) {
        return jsonObjects.stream().filter(jsonObject -> {
            try {
                return jsonObject.get(filter).equals(reference);
            } catch (JSONException ignored) {return false;}
        }).collect(Collectors.toList());

    }


    void launchResearchSelected() {
            startActivity(new Intent(getContext(), ResultActivity.class));
    }

    String setTitleItemListView(JSONObject json ) throws JSONException {
        if(BateauApplication.typeUtilisateurs.equals(TypeUtilisateurs.SKIPPER) ||
                BateauApplication.typeUtilisateurs.equals(TypeUtilisateurs.KITTER) )
            return "Coin sympas";
        else return json.get("fish").toString();

    }
    void swap() {
        if (advanced) getFragmentManager().beginTransaction().remove(advancedResearch).commit();
        else
            getFragmentManager().beginTransaction().replace(R.id.result_activity_linearLayout, advancedResearch).commit();
        advanced = !advanced;
    }

}
