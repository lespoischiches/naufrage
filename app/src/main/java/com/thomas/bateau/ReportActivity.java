package com.thomas.bateau;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;
import androidx.lifecycle.ReportFragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ReportActivity extends AppCompatActivity {

    private Button btnBack, btnMode;
    private boolean mode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_activity);
        btnBack = findViewById(R.id.btn_retour_signalement);
        btnBack.setOnClickListener(click ->{
            finish();
        });

        btnMode = findViewById(R.id.mode);


        if(findViewById(R.id.reportPort) != null) {
            Fragment frag = new com.thomas.bateau.ReportFragment();
            getFragmentManager().beginTransaction().replace(R.id.test, frag).commit();
            btnMode.setOnClickListener(click -> {
                if (!mode) {
                    Fragment fragmentDeuxMain = new com.thomas.bateau.ReportFragment();
                    getFragmentManager().beginTransaction().replace(R.id.test, fragmentDeuxMain).commit();
                } else {
                    Fragment fragmentUneMain = new ReportFragmentUneMain();
                    getFragmentManager().beginTransaction().replace(R.id.test, fragmentUneMain).commit();
                }
                mode = !mode;
            });
        }

        if(findViewById(R.id.reportLands) != null){

            Fragment fragmentUneMain = new ReportFragmentUneMain();
            getFragmentManager().beginTransaction().replace(R.id.test, fragmentUneMain).commit();
        }
    }
}