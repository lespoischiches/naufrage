package com.thomas.bateau.spot.spinner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.thomas.bateau.R;

public class SpinnerScientistFragment  extends SpinnerFragment  {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.spinner_scientist_fragment, container, false);
        init(v,R.id.spinnerFish,R.id.spinnerHour,R.id.spinnerDepth);
        return v;
    }
}
