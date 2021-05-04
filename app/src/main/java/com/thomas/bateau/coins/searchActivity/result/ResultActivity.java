package com.thomas.bateau.coins.searchActivity.result;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.thomas.bateau.R;

public class ResultActivity extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.researched_activity);
        findViewById(R.id.return_button_result).setOnClickListener(click-> finish());
    }
}
