package com.thomas.bateau.reportActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;

import android.os.Bundle;
import android.widget.Button;

import com.thomas.bateau.R;

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
            Fragment frag = new ReportFragmentDeuxMain();
            getFragmentManager().beginTransaction().replace(R.id.test, frag).commit();
            btnMode.setOnClickListener(click -> {
                if (!mode) getFragmentManager().beginTransaction().replace(R.id.test,  new ReportFragmentDeuxMain()).commit();
                else getFragmentManager().beginTransaction().replace(R.id.test, new ReportFragmentUneMain()).commit();
                mode = !mode;
            });
        }

        if(findViewById(R.id.reportLands) != null){

            ReportFragmentUneMain fragmentUneMain = new ReportFragmentUneMain();
            getFragmentManager().beginTransaction().replace(R.id.test, fragmentUneMain).commit();
        }
    }
}