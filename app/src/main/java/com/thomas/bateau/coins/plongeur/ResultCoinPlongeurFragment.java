package com.thomas.bateau.coins.plongeur;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.thomas.bateau.R;
import com.thomas.bateau.coins.ResultCoinFragment;
import com.thomas.bateau.coins.searchActivity.SearchActivity;
import com.thomas.bateau.coins.searchActivity.club.ClubSearchActivity;
import com.thomas.bateau.coins.searchActivity.spot.SpotSearchActivity;

public class ResultCoinPlongeurFragment extends ResultCoinFragment {

    private View fragView;

    public ResultCoinPlongeurFragment()
    {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragView=inflater.inflate(R.layout.coin_plongeur_result_fragment, container, false);


        return fragView;
    }
}
