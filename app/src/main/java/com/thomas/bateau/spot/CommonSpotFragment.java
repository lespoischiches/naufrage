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

import com.thomas.bateau.Character;
import com.thomas.bateau.FileManager;
import com.thomas.bateau.R;
import com.thomas.bateau.spot.spinner.SpinnerDiverFragment;
import com.thomas.bateau.spot.spinner.SpinnerFisherFragment;
import com.thomas.bateau.spot.spinner.SpinnerFragment;
import com.thomas.bateau.spot.spinner.SpinnerScientistFragment;
import com.thomas.bateau.spot.spinner.SpinnerSportFragment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class CommonSpotFragment extends Fragment  {

    ImageView imageView;
    EditText description;
    TextView textView;
    Bitmap bitmap;
    SpinnerFragment spinnerFragment;
    Character invokedType;
    String imagePath;
    final int REQUEST_CAMERA = 100;

    static HashMap<Integer, SpinnerFragment> spinnerID = new HashMap<>();
    static {
        spinnerID.put(Character.DIVER.ordinal(),new SpinnerDiverFragment());
        spinnerID.put(Character.FISHER.ordinal(),new SpinnerFisherFragment());
        spinnerID.put(Character.KITTER.ordinal(),new SpinnerSportFragment());
        spinnerID.put(Character.SKIPPER.ordinal(),new SpinnerSportFragment());
        spinnerID.put(Character.SCIENTIST.ordinal(),new SpinnerScientistFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.photo_fragment, container, false);
        invokedType = Character.values()[getActivity().getIntent().getExtras().getInt("ID")];
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
      //  getActivity().finish();
    }
    protected void setSpinner(SpinnerFragment spinnerFragment)
    {
        this.spinnerFragment = spinnerFragment;
        getFragmentManager().beginTransaction().replace(R.id.spinnerLayoutID,spinnerFragment).commit();
    }

    private void saveContent() {
        if (dataToJson() == null) return;
        ContextWrapper cw = new ContextWrapper(getActivity().getApplicationContext());
        File directory = cw.getDir("jsonDir", Context.MODE_PRIVATE);
        FileManager.saveFile(directory,dataToJson());
    }
    private void saveImage()
    {
        if(bitmap==null) return ;
        ContextWrapper cw = new ContextWrapper(getActivity().getApplicationContext());
        imagePath =  FileManager.saveImage(cw.getDir("spotImageDir", Context.MODE_PRIVATE),bitmap);
    }


    private void setPosition()
    {
        Double[] position = ((CommonSpotActivity) getActivity()).getLocation();
        if (position == null) textView.setText("je n'ai pas accès au gps");
        else textView.setText("Position\nlatitude :"+position[0]+"\nlongitude :"+position[1]);
    }

    private void initUI(View view) {
        view.findViewById(R.id.spot_return).setOnClickListener(click-> getActivity().finish());
        view.findViewById(R.id.add_photo).setOnClickListener(click -> pictureAction(view));
        view.findViewById(R.id.post).setOnClickListener(click -> postAction());

        imageView = view.findViewById(R.id.image_spot);
        description = view.findViewById(R.id.description);
        textView = view.findViewById(R.id.localPos);

        setSpinner(spinnerID.get(((CommonSpotActivity)getActivity()).getIntent().getExtras().getInt("ID")));

        initPosition();

    }

    void initPosition()
    {
        CommonSpotActivity activity = ((CommonSpotActivity) getActivity());
        activity.setOnNewLocationCallBack(this::setPosition);
        Double[] location = activity.getSavedLocation();
        if(location!=null) textView.setText(Arrays.toString(location));
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
        Double[] position =  ((CommonSpotActivity) getActivity()).getSavedLocation();
        data.append(convertJson("type",String.valueOf(invokedType.ordinal())))
                .append(",")
                .append(convertJson("image",imagePath))
                .append(",")
                .append(convertJson("description",description.getText().toString()))
                .append(",")
                .append(convertJson("position","["+position[0]+","+position[1]+"]"))
                .append(",")
                .append(spinnerFragment.dataToJson()).append("}");
        return data.toString();
    }
    private String  convertJson(String key, String element)
    {
        return "\""+key+"\":\""+element+"\"";
    }
}


