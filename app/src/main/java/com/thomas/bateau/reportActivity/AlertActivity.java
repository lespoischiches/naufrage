package com.thomas.bateau.reportActivity;

import android.os.Bundle;

import com.thomas.bateau.R;

public class AlertActivity extends ReportActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_activity);
        initReturnBack(R.id.btn_retour_signalement);
        initLayout(R.id.reportPort ,R.id.reportLands );
    }
}