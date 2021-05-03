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

import com.thomas.bateau.R;

import java.util.HashMap;

public abstract class SpinnerFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    String fishingChoice,hourChoice, depthChoice, typeofFishingChoice ;
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
        for(Integer spinnerID : spinnersID) initSpinner(v,spinnerID,spinners.get(spinnerID));
    }


    private void initSpinner(View v,int ID,int arrayID)
    {
        Spinner lst = v.findViewById(ID);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),arrayID,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lst.setAdapter(adapter);
        lst.setOnItemSelectedListener(this);

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
            case R.id.spinnerTypeOfFishing :
                typeofFishingChoice = position==0? null : parent.getItemAtPosition(position).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
