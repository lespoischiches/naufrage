package com.thomas.bateau.spot.spinner;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.thomas.bateau.BateauApplication;
import com.thomas.bateau.Character;
import com.thomas.bateau.R;
import com.thomas.bateau.spot.CommonSpotActivity;
import com.thomas.bateau.spot.CommonSpotFragment;

import java.util.HashMap;

public abstract class SpinnerFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    protected SpinnerData spinnerData;
    static HashMap<Integer,Integer> spinners = new HashMap<>();
    static
    {
        spinners.put(R.id.spinnerFish,R.array.spinnerFish);
        spinners.put(R.id.spinnerHour,R.array.spinnerHour);
        spinners.put(R.id.spinnerDepth,R.array.spinnerDepth);
        spinners.put(R.id.spinnerTypeOfFishing,R.array.spinnerTypeOfFishing);
    }

    void init(View v, Integer... spinnersID)
    {

        spinnerData= new SpinnerData((((CommonSpotActivity)getActivity()).getIntent().getExtras().getInt("ID")));
        for(Integer spinnerID : spinnersID) initSpinner(v,spinnerID,spinners.get(spinnerID));
    }



    private void initSpinner(View v,int ID,int arrayID) {
        Spinner lst = v.findViewById(ID);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),arrayID,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lst.setAdapter(adapter);
        lst.setOnItemSelectedListener((SpinnerFragment)this);
    }
    public String dataToJson()
    {
        return spinnerData.toJson();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        if(position!=0) spinnerData.onItemSelected(parent.getId(), parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
