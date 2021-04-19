package com.thomas.bateau.reportActivity;

import android.os.Bundle;

import com.thomas.bateau.R;

public class SpotActivity extends ReportActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spot_activity);
        initReturnBack(R.id.btn_retour_spot);
        initLayout(R.id.spotReport ,R.id.spotReportLand );
    }
}
