package com.thomas.bateau.spot;

import android.os.Bundle;

import com.thomas.bateau.R;

public class SkipperSpotActivity extends CommonSpotActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skipper_spot);

        init(R.id.skipper_spot_return);
    }
}