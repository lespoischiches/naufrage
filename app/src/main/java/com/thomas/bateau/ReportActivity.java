package com.thomas.bateau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ReportActivity extends AppCompatActivity {

    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_activity);
        btnBack = findViewById(R.id.btn_retour_signalement);
        btnBack.setOnClickListener(click ->{
            finish();
        });
    }
}