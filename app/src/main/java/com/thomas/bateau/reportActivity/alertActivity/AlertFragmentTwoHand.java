package com.thomas.bateau.reportActivity.alertActivity;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thomas.bateau.R;

public class AlertFragmentTwoHand extends AlertFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_report_deux_main, container, false);
        init(v);
        return v;
    }
}