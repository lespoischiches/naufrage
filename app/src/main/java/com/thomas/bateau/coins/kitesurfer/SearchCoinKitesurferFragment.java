package com.thomas.bateau.coins.kitesurfer;

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

public class SearchCoinKitesurferFragment extends SearchCoinFragment {

    private View fragView;

    public SearchCoinKitesurferFragment()
    {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragView=inflater.inflate(R.layout.coin_kitsufer_search_fragment, container, false);
        Button button = fragView.findViewById(R.id.btRechercheUnSpotDeKitesurfersSearch);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(fragView.getContext(), SpotSearchActivity.class));
            }
        });
        return fragView;
    }
}