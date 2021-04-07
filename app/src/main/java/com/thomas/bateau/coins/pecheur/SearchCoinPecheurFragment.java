package com.thomas.bateau.coins.pecheur;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.thomas.bateau.R;
import com.thomas.bateau.coins.SearchCoinFragment;

public class SearchCoinPecheurFragment extends SearchCoinFragment {
    private View fragView;

    public SearchCoinPecheurFragment()
    {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragView=inflater.inflate(R.layout.coin_pecheur_search_fragment, container, false);
        return fragView;
    }
}
