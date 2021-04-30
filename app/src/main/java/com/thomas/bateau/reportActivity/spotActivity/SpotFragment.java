package com.thomas.bateau.reportActivity.spotActivity;

import android.view.View;

import com.thomas.bateau.R;
import com.thomas.bateau.reportActivity.ReportFragment;

public class SpotFragment extends ReportFragment {


    @Override
    protected void init(View v) {
        initUi(v, R.id.fish_spot);
        initUi(v, R.id.scientist_spot);
        initUi(v, R.id.skipper_spot);

        initUi(v, R.id.diver_spot);
        initUi(v, R.id.kitter_spot);
    }
}
