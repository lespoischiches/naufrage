package com.thomas.bateau.reportActivity.spotActivity;

import android.os.Bundle;

import com.thomas.bateau.R;
import com.thomas.bateau.reportActivity.ReportActivity;
import com.thomas.bateau.reportActivity.alertActivity.AlertFragmentOneHand;
import com.thomas.bateau.reportActivity.alertActivity.AlertFragmentTwoHand;

public class SpotActivity extends ReportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spot_activity);
        initReturnBack(R.id.btn_retour_spot);
        initLayout(R.id.spotReport ,R.id.spotReportLand );

    }
    @Override
    protected void initLayout(int portID, int landID) {
        super.initLayout(portID,landID);
        if(findViewById(portID)==null)return;
        setButtonMode(new SpotFragmentOneHand(),new SpotFragmentTwoHand());
    }
}
