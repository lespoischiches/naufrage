package com.thomas.bateau.coins.searchActivity.result;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.thomas.bateau.R;

import java.io.File;

public class ResultActivity extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        findViewById(R.id.return_button_result).setOnClickListener(click-> finish());
        ((TextView)findViewById(R.id.titleResult)).setText((CharSequence) getIntent().getExtras().get("titre"));
        ((TextView)findViewById(R.id.description_result)).setText((CharSequence) getIntent().getExtras().get("description"));
        File imgFile = new  File((String) getIntent().getExtras().get("url"));

        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ImageView myImage = (ImageView) findViewById(R.id.image_saved);
            myImage.setImageBitmap(myBitmap);
        }

    }
}
