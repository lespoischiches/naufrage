package com.thomas.bateau.coins.searchActivity.advancedR;

import android.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.thomas.bateau.FileManager;
import com.thomas.bateau.R;
import com.thomas.bateau.coins.searchActivity.SearchFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AdvancedResearchFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    static HashMap<Integer,Integer> spinners = new HashMap<>();
    static
    {
        spinners.put(R.id.hour_filter,R.array.hour_filter);
        spinners.put(R.id.fish_filter,R.array.fish_filter);
        spinners.put(R.id.depth_filter,R.array.depth_filter);
        spinners.put(R.id.fishing_way_filter,R.array.fishing_way_filter);
    }
    static HashMap<Integer,String> filter = new HashMap<>();
    static
    {
        filter.put(R.id.hour_filter,"hours");
        filter.put(R.id.fish_filter,"fish");
        filter.put(R.id.depth_filter,"depth");
        filter.put(R.id.fishing_way_filter,"fishingM");
    }
    List<String> filterList;
    SearchFragment parent;

    void init(View v, Integer... spinnersID)
    {
        filterList = new ArrayList<>();
        for(Integer spinnerID : spinnersID) initSpinner(v,spinnerID,spinners.get(spinnerID));
    }


    private void initSpinner(View v,int ID,int arrayID) {
        Spinner lst = v.findViewById(ID);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),arrayID,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lst.setAdapter(adapter);
        lst.setOnItemSelectedListener(this);

    }

    public void setParent(SearchFragment parent) {
        this.parent = parent;
    }

    @Override
    public  void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {

        if(id!=0  && !filterList.contains(filter.get(id)))return;
        if(filterList.contains(filter.get(id)))filterList.remove(filter.get(id));
        else filterList.add(filter.get(id));

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
