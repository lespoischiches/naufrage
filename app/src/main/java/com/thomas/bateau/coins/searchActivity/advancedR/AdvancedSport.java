package com.thomas.bateau.coins.searchActivity.advancedR;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.thomas.bateau.R;

public class AdvancedSport extends AdvancedResearchFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.advanced_sport_fragment, container, false);
        init(v,R.id.hour_filter);
        return v;
    }

}
