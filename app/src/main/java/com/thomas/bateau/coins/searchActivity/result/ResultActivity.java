package com.thomas.bateau.coins.searchActivity.result;

import android.app.Activity;
import android.os.Bundle;

import com.thomas.bateau.R;

public class ResultActivity extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        findViewById(R.id.return_button_result).setOnClickListener(click-> finish());
    }
}
