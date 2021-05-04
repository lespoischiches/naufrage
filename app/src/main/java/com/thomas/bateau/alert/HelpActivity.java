package com.thomas.bateau.alert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.thomas.bateau.R;

public class HelpActivity extends Activity {



    Button post, buttonBack ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_activity_help);

        buttonBack = findViewById(R.id.alert_fragment_return);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ((TextView) findViewById(R.id.alert_fragment_titre)).setText("Signaler un besoin d'aide");

    }
}
