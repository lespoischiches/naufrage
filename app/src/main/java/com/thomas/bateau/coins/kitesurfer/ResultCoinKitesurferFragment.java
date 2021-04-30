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
import com.thomas.bateau.coins.ResultCoinFragment;
import com.thomas.bateau.coins.resultactivity.ResultFisherActivity;
import com.thomas.bateau.coins.resultactivity.ResultKitterActivity;

public class ResultCoinKitesurferFragment extends ResultCoinFragment {

    private View fragView;

    public ResultCoinKitesurferFragment()
    {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragView = inflater.inflate(R.layout.coin_kitesurfer_result_fragment, container, false);
        Button button = fragView.findViewById(R.id.btRechercheUnClubDesKitesurfersResult);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(fragView.getContext(), ResultKitterActivity.class));
            }
        });
        return fragView;
    }
}