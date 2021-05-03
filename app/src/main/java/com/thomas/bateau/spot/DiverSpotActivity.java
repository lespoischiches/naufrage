package com.thomas.bateau.spot;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.thomas.bateau.R;

public class DiverSpotActivity extends CommonSpotActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diver_spot);

        init(R.id.diver_spot_return);



    }
}