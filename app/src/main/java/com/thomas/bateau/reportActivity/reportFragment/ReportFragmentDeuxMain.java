package com.thomas.bateau.reportActivity.reportFragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thomas.bateau.R;

public class ReportFragmentDeuxMain extends ReportFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_report_deux_main, container, false);
        initUIEmergency(v);
        initUINaufrage(v);
        return v;
    }
}