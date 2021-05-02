package com.thomas.bateau.spot;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public abstract class PostSpotActivity extends AppCompatActivity {

    protected Button btnBack, btnPicture;
    protected final int REQUEST_CAMERA = 100;
    protected ImageView imageView;
    protected Bitmap picture = null;

    protected void initReturnBack(int backID)
    {
        this.picture = null;
        btnBack = findViewById(backID);
        btnBack.setOnClickListener(click ->{
            finish();
        });
    }

    protected void addPicture(int takePictureBtnId, int imageViewId) {
        btnPicture = findViewById(takePictureBtnId);
        imageView = findViewById(imageViewId);
        btnPicture.setOnClickListener(click ->{
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.CAMERA},
                        REQUEST_CAMERA);
            }
            else {
                takePicture();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] granResults) {
        switch (requestCode) {
            case REQUEST_CAMERA : {
                if(granResults.length > 0 && granResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast toast = Toast.makeText(getApplicationContext(),"CAMERA authorization granted", Toast.LENGTH_LONG);
                    toast.show();
                    takePicture();
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),"CAMERA authorization NOT granted", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if( requestCode == REQUEST_CAMERA) {
            if (resultCode == RESULT_OK) {
                picture = (Bitmap) data.getExtras().get("data");
                setImage(picture);
            }
            else if (resultCode == RESULT_CANCELED) {
                Toast toast = Toast.makeText(getApplicationContext(),"picture canceled", Toast.LENGTH_LONG);
                toast.show();
            }
            else {
                Toast toast = Toast.makeText(getApplicationContext(),"picture failed", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }


    public void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    public void setImage(Bitmap bitmap) {
        this.picture = bitmap;
        this.imageView.setImageBitmap(bitmap);
    }
}
