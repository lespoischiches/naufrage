package com.thomas.bateau.spot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thomas.bateau.R;

public class KitterSpotActivity extends PostSpotActivity {


    Button post, buttonBack ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitter_spot);

        buttonBack = findViewById(R.id.kitter_spot_return);
        initReturnBack(R.id.diver_spot_return);

        post =findViewById(R.id.post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        addPicture(R.id.add_photo,R.id.image_spot);
    }
}