package com.thomas.bateau.reportActivity.alertActivity;

import android.view.View;

import com.thomas.bateau.R;
import com.thomas.bateau.reportActivity.ReportFragment;

public class AlertFragment extends ReportFragment {

    @Override
    protected void init(View v)
    {
        initUi(v, R.id.emergency);
        initUi(v,R.id.naufrage);
        initUi(v, R.id.meteo);
        initUi(v, R.id.remains);
        initUi(v, R.id.help);

    }
}
