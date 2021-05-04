package com.thomas.bateau.reportActivity.alertActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.thomas.bateau.R;
import com.thomas.bateau.coins.SearchCoinFragment;
import com.thomas.bateau.coins.searchActivity.spot.SpotSearchActivity;

public class AlertActivityFragment extends SearchCoinFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragView =inflater.inflate(R.layout.alert_activity_fragment, container, false);
        return fragView;
    }
}

