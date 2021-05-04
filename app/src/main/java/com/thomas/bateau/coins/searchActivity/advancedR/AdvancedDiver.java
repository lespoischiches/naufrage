package com.thomas.bateau.coins.searchActivity.advancedR;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.thomas.bateau.R;
import com.thomas.bateau.coins.searchActivity.JsonFilter;

import java.util.ArrayList;

public class AdvancedDiver extends AdvancedResearchFragment{



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.advanced_diver_fragment, container, false);
        init(v,R.id.fish_filter,R.id.hour_filter,R.id.depth_filter);
        return v;
    }


}
