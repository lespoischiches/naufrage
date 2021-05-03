package com.thomas.bateau.spot;

import android.os.Bundle;

import com.thomas.bateau.R;

public class ScientistSpotActivity extends CommonSpotActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science_spot);

        init(R.id.scientist_spot_return);
    }
}