package com.thomas.bateau.coins.scientifique;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.thomas.bateau.R;

import com.thomas.bateau.coins.SearchCoinFragment;

public class SearchCoinScientifiqueFragment extends SearchCoinFragment {
    private View fragView;

    public SearchCoinScientifiqueFragment()
    {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragView=inflater.inflate(R.layout.coin_scientifique_search_fragment, container, false);
        return fragView;
    }
}

