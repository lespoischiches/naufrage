package com.thomas.bateau;

import android.content.Intent;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.thomas.bateau.coins.CoinActivity;

public class ReportFragment extends Fragment {
    public Button signal1btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View reportView = inflater.inflate(R.layout.fragment_report, container, false);
        signal1btn = reportView.findViewById(R.id.button);
        return reportView;
    }
}