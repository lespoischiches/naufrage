package com.thomas.bateau.coins.searchActivity;

import android.app.Fragment;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.thomas.bateau.BateauApplication;
import com.thomas.bateau.FileManager;
import com.thomas.bateau.R;
import com.thomas.bateau.TypeUtilisateurs;
import com.thomas.bateau.coins.ItemListView;
import com.thomas.bateau.coins.ItemListViewAdapter;
import com.thomas.bateau.coins.searchActivity.advancedR.AdvancedDiver;
import com.thomas.bateau.coins.searchActivity.advancedR.AdvancedFisher;
import com.thomas.bateau.coins.searchActivity.advancedR.AdvancedResearchFragment;
import com.thomas.bateau.coins.searchActivity.advancedR.AdvancedScientist;
import com.thomas.bateau.coins.searchActivity.advancedR.AdvancedSport;
import com.thomas.bateau.coins.searchActivity.result.ResultActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SearchFragment extends Fragment {


    Button buttonResearch;
    EditText text;
    public List<JSONObject> elements;
    LinkedList<ItemListView> items;
    ListView listView;
    boolean advanced = false;
    AdvancedResearchFragment spinnerFragment;
    ItemListViewAdapter adapter;
    View currentView;

    public static HashMap<Integer, AdvancedResearchFragment> spinnerID = new HashMap<>();
    static {

        spinnerID.put(TypeUtilisateurs.PECHEUR.ordinal(),new AdvancedFisher());
        spinnerID.put(TypeUtilisateurs.PLONGEUR.ordinal(),new AdvancedDiver());
        spinnerID.put(TypeUtilisateurs.SKIPPER.ordinal(),new AdvancedSport());
        spinnerID.put(TypeUtilisateurs.KITTER.ordinal(),new AdvancedSport());
        spinnerID.put(TypeUtilisateurs.SCIENTIFIQUE.ordinal(),new AdvancedScientist());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.search_fragment, container, false);
        v.findViewById(R.id.result_return).setOnClickListener(click -> getActivity().finish());
        initListJson();
        initSpinnerFragment();
        initSearchButton(v);
        initShowMoreButton(v);
        initSearchButton(v);
        text = v.findViewById(R.id.edit_button_search);
        try {
            initListView(v);
        }catch ( JSONException ignored){}
        v.findViewById(R.id.result_activity_linearLayout).setVisibility(View.VISIBLE);
        currentView = v;
        return v;
    }

    void initShowMoreButton(View v)
    {
        buttonResearch = v.findViewById(R.id.show_more_button);
        buttonResearch.setText("Recherche avanc?? ");
        buttonResearch.setOnClickListener(click -> swap());
    }
    void initSpinnerFragment()
    {
        this.spinnerFragment = spinnerID.get(BateauApplication.typeUtilisateurs.ordinal());
        spinnerFragment.setParentFragment(this);
    }
    void initSearchButton(View v)
    {
        v.findViewById(R.id.search_button).setOnClickListener(click->{
            items.clear();
            for (JSONObject jsonObject :JsonFilter.filterJsonObjects(elements, JsonFilter::findRegex,text.getText().toString(),"fish","description","title")) {
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
            List<JSONObject> resources = FileManager.loadFile(contextWrapper.getDir("jsonDir", Context.MODE_PRIVATE).toString(),FileManager.SPOT);
            elements = JsonFilter.filterJsonObjects(resources, String::equals,  String.valueOf(BateauApplication.typeUtilisateurs.ordinal()),"type");
        } catch (IOException ioException) { ioException.printStackTrace(); }
    }

    void initListView(View v) throws  JSONException {
        adapter = new ItemListViewAdapter(getActivity().getApplicationContext(), R.layout.search_activity, fillListView());
        listView = (ListView) v.findViewById(R.id.result_activity_listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(((parent, view, position, id) -> launchResearchSelected((ItemListView) parent.getItemAtPosition(position))));
    }


    LinkedList<ItemListView> fillListView() throws JSONException {
        items = new LinkedList<>();
        for (JSONObject jsonObject :elements) items.add(createItemListView(jsonObject));
        return items;
    }




    private ItemListView createItemListView(JSONObject jsonObject) throws JSONException
    {
        ItemListView itemListView = new ItemListView(setTitleItemListView(jsonObject),BateauApplication.typeUtilisateurs.getIcon(), jsonObject.get("description").toString());

        if(!jsonObject.isNull("image")) itemListView.setUrl((String)jsonObject.get("image").toString());
        if(!jsonObject.isNull("depth")) itemListView.setDepth((String)jsonObject.get("depth").toString());
        if(!jsonObject.isNull("fishingM")) itemListView.setFishingWay((String) jsonObject.get("fishingM").toString());
        if(!jsonObject.isNull("hours"))  itemListView.setHour((String) jsonObject.get("hours").toString());
        return itemListView;
    }


    public void updateView(List<JSONObject> jsonObjects) throws JSONException
    {
        items.clear();
        for (JSONObject jsonObject :jsonObjects) items.add(createItemListView(jsonObject));
        ItemListViewAdapter adapter = new ItemListViewAdapter(getActivity().getApplicationContext(), R.layout.search_activity, items);
        listView.setAdapter(adapter);


    }
    void launchResearchSelected(ItemListView item) {
            startActivity(new Intent(getContext(), ResultActivity.class)
                    .putExtra("url",item.getUrl())
                    .putExtra("description",item.getDescription())
                    .putExtra("titre",item.getNom())
            );
    }

    private String setTitleItemListView(JSONObject json ) throws JSONException {
        if(BateauApplication.typeUtilisateurs.equals(TypeUtilisateurs.SKIPPER) ||
                BateauApplication.typeUtilisateurs.equals(TypeUtilisateurs.KITTER) )
            return json.get("title").toString();

        else return json.get("fish").toString();

    }
    private void swap() {

        if (advanced) {
            getFragmentManager().beginTransaction().remove(spinnerFragment).commit();
            currentView.findViewById(R.id.result_activity_linearLayout).setVisibility(View.VISIBLE);
            try {
                updateView(elements);
            }catch (JSONException exception){}

        }
        else {
            getFragmentManager().beginTransaction().replace(R.id.frame_test, spinnerFragment).commit();
            currentView.findViewById(R.id.result_activity_linearLayout).setVisibility(View.INVISIBLE);
        }
        advanced = !advanced;
    }

}
