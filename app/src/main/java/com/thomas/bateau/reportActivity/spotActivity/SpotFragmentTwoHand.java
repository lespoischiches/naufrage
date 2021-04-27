package com.thomas.bateau.reportActivity.spotActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thomas.bateau.R;

public class SpotFragmentTwoHand extends SpotFragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_spot_two_hand, container, false);
        init(v);
        return v;
    }
}
