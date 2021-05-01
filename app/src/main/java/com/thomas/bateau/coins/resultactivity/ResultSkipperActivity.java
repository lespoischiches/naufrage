package com.thomas.bateau.coins.resultactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thomas.bateau.R;

public class ResultSkipperActivity extends Activity {



    Button post, buttonBack ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity_skipper);

        buttonBack = findViewById(R.id.result_activity_skipper_return);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}