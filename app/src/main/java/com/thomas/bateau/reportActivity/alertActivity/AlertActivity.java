package com.thomas.bateau.reportActivity.alertActivity;

import android.app.Fragment;
import android.os.Bundle;

import com.thomas.bateau.R;
import com.thomas.bateau.reportActivity.ReportActivity;

public class AlertActivity extends ReportActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_activity);
        initReturnBack(R.id.btn_retour_signalement);
        initLayout(R.id.reportPort ,R.id.reportLands );
    }

    @Override
    protected void initLayout(int portID, int landID) {
        super.initLayout(portID,landID);
        if(findViewById(portID)==null)return;
        setButtonMode(new AlertFragmentOneHand(),new AlertFragmentTwoHand());

    }
}