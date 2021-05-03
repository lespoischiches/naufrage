package com.thomas.bateau.spot.spinner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thomas.bateau.R;

public class SpinnerSportFragment extends SpinnerFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.spinner_sport_fragment, container, false);
        init(v,R.id.spinnerHour);
        return v;
    }
}
