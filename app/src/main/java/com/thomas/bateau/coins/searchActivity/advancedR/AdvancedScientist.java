package com.thomas.bateau.coins.searchActivity.advancedR;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.thomas.bateau.R;

public class AdvancedScientist extends AdvancedResearchFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.advanced_scientist_fragment, container, false);
        init(v,R.id.fish_filter,R.id.hour_filter,R.id.depth_filter);
        return v;
    }



}
