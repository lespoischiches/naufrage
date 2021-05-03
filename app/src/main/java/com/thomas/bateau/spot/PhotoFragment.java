package com.thomas.bateau.spot;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.thomas.bateau.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class PhotoFragment extends Fragment  implements AdapterView.OnItemSelectedListener {

    ImageView imageView;
    EditText description;
    TextView textView;
    Bitmap bitmap;
    String fishingChoice,hourChoice, depthChoice ;

    final int REQUEST_CAMERA = 100;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.photo_fragment, container, false);
        fishingChoice = null;
        hourChoice = null;
        depthChoice = null;
        initUI(v);
        return v;
    }


    void pictureAction(View view) {
        if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
        else startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_CAMERA);
    }

    void postAction() {
        saveImage();
        saveContent();
        getActivity().finish();
    }

    private void saveContent() {
        if (dataToJson() == null) return;
        ContextWrapper cw = new ContextWrapper(getActivity().getApplicationContext());
        File directory = cw.getDir("jsonDir", Context.MODE_PRIVATE);
        try {
            FileOutputStream fos = getContext().openFileOutput(directory + "1.json", Context.MODE_PRIVATE);
            fos.write( dataToJson().getBytes());
            fos.close();
        } catch (IOException fileNotFoundException){}
    }
    private void saveImage()
    {
        if(bitmap==null) return;
        ContextWrapper cw = new ContextWrapper(getActivity().getApplicationContext());
        File directory = cw.getDir("spotImageDir", Context.MODE_PRIVATE);
        File path = new File(directory, Objects.requireNonNull(directory.listFiles()).length+1+".png");
        try {
            FileOutputStream fos = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace(); }
        Arrays.stream(Objects.requireNonNull(directory.listFiles())).forEach(file -> Log.d("fichier",file.getAbsolutePath()));
    }


    private void setPosition()
    {
        Double[] position = ((CommonSpotActivity) getActivity()).getLocation();
        if (position == null) textView.setText("je n'ai pas accès au gps");
        else textView.setText("Position\nlatitude :"+position[0]+"\nlongitude :"+position[1]);
    }
    private void initUI(View view) {
        view.findViewById(R.id.add_photo).setOnClickListener(click -> pictureAction(view));
        imageView = view.findViewById(R.id.image_spot);
        description = view.findViewById(R.id.description);
        textView = view.findViewById(R.id.localPos);
        view.findViewById(R.id.post).setOnClickListener(click -> postAction());

        initSpinner(view,R.id.spinnerFish,R.array.spinnerFish);
        initSpinner(view,R.id.spinnerHour,R.array.spinnerHour);
        initSpinner(view,R.id.spinnerDepth,R.array.spinnerDepth);

        CommonSpotActivity activity = ((CommonSpotActivity) getActivity());
        activity.setOnNewLocationCallBack(this::setPosition);
        Double[] location = activity.getSavedLocation();
        if(location!=null) textView.setText(Arrays.toString(location));
    }
    private void initSpinner(View v,int id,int layoutID)
    {
        Spinner lst = v.findViewById(id);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),layoutID,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lst.setAdapter(adapter);
        lst.setOnItemSelectedListener(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != REQUEST_CAMERA) return;
        switch (resultCode) {
            case RESULT_OK:
                bitmap= (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);
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
        String hour = convertJson("hour",hourChoice);
        String depth = convertJson("depth",depthChoice);
        String fish = convertJson("fish",fishingChoice);
        String comment = convertJson("comment" , description.getText().toString());
        return hour+","+depth+","+fish+","+comment;
    }
    String  convertJson(String key, String element)
    {
        return "{'"+key+"':"+element+"}";
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        switch (parent.getId()){
            case R.id.spinnerFish :
                fishingChoice = position==0? null : parent.getItemAtPosition(position).toString();
            case R.id.spinnerHour :
                hourChoice = position==0? null : parent.getItemAtPosition(position).toString();
            case R.id.spinnerDepth :
                depthChoice = position==0? null : parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


