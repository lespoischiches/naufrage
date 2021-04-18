package com.thomas.bateau;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.thomas.bateau.meteo.MeteoActivity;

public class ReportFragmentUneMain extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_une_main_report, container, false);

        initUIEmergency(v);
        initUINaufrage(v);

        return v;
    }

    private void initUIEmergency(View v){
        Button emergency =(Button)v.findViewById(R.id.emergency);
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0987654321"));
                startActivity(intent);

            }
        });
    }

    private void initUINaufrage(View v){
        Button emergency =(Button)v.findViewById(R.id.naufrage);
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), NaufrageActivity.class);
                startActivity(intent);

            }
        });
    }




}
