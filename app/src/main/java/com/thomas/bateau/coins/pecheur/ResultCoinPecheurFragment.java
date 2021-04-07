package com.thomas.bateau.coins.pecheur;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.thomas.bateau.R;
import com.thomas.bateau.coins.ResultCoinFragment;


public class ResultCoinPecheurFragment extends ResultCoinFragment {

    private View fragView;

    public ResultCoinPecheurFragment()
    {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragView=inflater.inflate(R.layout.coin_pecheur_result_fragment, container, false);
        return fragView;
    }
}
