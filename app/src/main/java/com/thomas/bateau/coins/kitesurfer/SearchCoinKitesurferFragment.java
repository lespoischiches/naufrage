package com.thomas.bateau.coins.kitesurfer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.thomas.bateau.R;
import com.thomas.bateau.coins.SearchCoinFragment;

public class SearchCoinKitesurferFragment extends SearchCoinFragment {

    private View fragView;

    public SearchCoinKitesurferFragment()
    {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragView=inflater.inflate(R.layout.coin_kitsufer_search_fragment, container, false);
        return fragView;
    }
}