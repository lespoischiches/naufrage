package com.thomas.bateau.reportActivity.alertActivity.alert;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.thomas.bateau.FileManager;
import com.thomas.bateau.R;


import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class AlertActivityFragment extends Fragment {

    String imagePath;
    Bitmap bitmap;
    EditText description;
    ImageView imageView;
    final int REQUEST_CAMERA = 100;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragView =inflater.inflate(R.layout.alert_activity_fragment, container, false);
        initUI(fragView);
        return fragView;
    }


    private void initUI(View view) {
        view.findViewById(R.id.alert_fragment_return).setOnClickListener(click-> getActivity().finish());
        view.findViewById(R.id.add_photo).setOnClickListener(click -> pictureAction(view));
        view.findViewById(R.id.post).setOnClickListener(click -> postAction());

        imageView = view.findViewById(R.id.image_spot);
        description = view.findViewById(R.id.description);

    }

    void postAction() {
        saveImage();
        saveContent();
        getActivity().finish();
    }

    private void saveContent() {
        FileManager.saveFile( new ContextWrapper(getActivity().getApplicationContext()),dataToJson(),FileManager.ALERT);
    }
    private void saveImage()
    {
        if(bitmap==null) return ;
        ContextWrapper cw = new ContextWrapper(getActivity().getApplicationContext());
        imagePath =  FileManager.saveImage(cw.getDir("alertImageDir", Context.MODE_PRIVATE),bitmap);
    }


    void pictureAction(View view) {
        if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
        else startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_CAMERA);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != REQUEST_CAMERA) return;
        switch (resultCode) {
            case RESULT_OK:
                imageView.setImageBitmap(bitmap= (Bitmap) data.getExtras().get("data"));
                break;
            case RESULT_CANCELED:
                toastShow("picture canceled");
                break;
            default:
                toastShow("picture failed");
                break;
        }
    }

    private void toastShow(String information) {
        Toast.makeText(getActivity().getApplicationContext(), information, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] granResults) {
        super.onRequestPermissionsResult(requestCode, permissions, granResults);
        if (requestCode != REQUEST_CAMERA) return;

        if (granResults.length > 0 && granResults[0] == PackageManager.PERMISSION_GRANTED) {
            toastShow("CAMERA authorization granted");
            startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_CAMERA);
        } else toastShow("CAMERA authorization NOT granted");
    }









    public String dataToJson()
    {
        StringBuilder data  =new StringBuilder("{");
        data.append(convertJson("type",String.valueOf(getActivity().getIntent().getExtras().get("type"))))
                .append(",")
                .append(convertJson("image",imagePath))
                .append(",")
                .append(convertJson("description",description.getText().toString()))
                .append("}\n");
        return data.toString();
    }
    private String  convertJson(String key, String element)
    {
        return "\""+key+"\":\""+element+"\"";
    }
}

