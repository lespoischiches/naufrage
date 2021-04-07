package com.thomas.bateau.coins.kitesurfer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.thomas.bateau.R;
import com.thomas.bateau.coins.ResultCoinFragment;

public class ResultCoinKitesurferFragment extends ResultCoinFragment {

    private View fragView;

    public ResultCoinKitesurferFragment()
    {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragView = inflater.inflate(R.layout.coin_kitesurfer_result_fragment, container, false);
        return fragView;
    }
}