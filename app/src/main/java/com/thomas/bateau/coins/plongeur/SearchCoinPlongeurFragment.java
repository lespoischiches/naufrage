package com.thomas.bateau.coins.plongeur;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.thomas.bateau.R;
import com.thomas.bateau.coins.SearchCoinFragment;

public class SearchCoinPlongeurFragment extends SearchCoinFragment {
    private View fragView;

    public SearchCoinPlongeurFragment()
    {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragView=inflater.inflate(R.layout.coin_plongeur_search_fragment, container, false);
        return fragView;
    }
}



