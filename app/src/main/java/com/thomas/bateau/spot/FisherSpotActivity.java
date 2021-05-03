package com.thomas.bateau.spot;

import android.os.Bundle;
import android.widget.Button;

import com.thomas.bateau.R;

public class FisherSpotActivity extends CommonSpotActivity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisher_spot);

        init(R.id.fisher_spot_return);
    }
}
