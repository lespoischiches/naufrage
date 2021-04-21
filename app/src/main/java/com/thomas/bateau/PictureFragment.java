package com.thomas.bateau;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class PictureFragment extends Fragment {

    private View fragPictureView;
    private ImageView imageView;
    private Button takePictureButton;
    static final int REQUEST_CAMERA = 100;

    public PictureFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragPictureView = inflater.inflate(R.layout.fragment_picture, container, false);
        imageView = fragPictureView.findViewById(R.id.image_spot);
        takePictureButton =  fragPictureView.findViewById(R.id.btn_takePicture);
        takePictureButton.setOnClickListener(click ->{
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)== PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[] {Manifest.permission.CAMERA},
                        REQUEST_CAMERA);
            }
            else {
                takePicture();
            }
        });
        return fragPictureView;
    }

    public void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        getActivity().startActivityForResult(intent, REQUEST_CAMERA);
    }

    public void setImage(Bitmap bitmap) { imageView.setImageBitmap(bitmap);}

}
