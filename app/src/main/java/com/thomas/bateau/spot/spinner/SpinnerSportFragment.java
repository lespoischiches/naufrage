package com.thomas.bateau.spot.spinner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;

import com.thomas.bateau.R;

public class SpinnerSportFragment extends SpinnerFragment  {


    EditText text;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.spinner_sport_fragment, container, false);
        init(v,R.id.spinnerHour);
        text = v.findViewById(R.id.title_sport);
        return v;
    }
    @Override
    public String dataToJson() {
        return super.dataToJson()+","+convertJson("title",text.getText().toString());
    }
    private String  convertJson(String key, String element)
    {
        return "\""+key+"\":\""+element+"\"";
    }
}
