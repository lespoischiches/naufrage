package com.thomas.bateau.spot.spinner;

import com.thomas.bateau.Character;
import com.thomas.bateau.R;

import java.util.HashMap;

public class SpinnerData {

    String fishingChoice,hourChoice, depthChoice ,typeofFishingChoice;
    int characterID;

    public SpinnerData(int id)
    {
        characterID = id ;
    }


    void onItemSelected(int parentId,String value)
    {
        switch (parentId){
            case R.id.spinnerFish :
                fishingChoice = value;
                break;
            case R.id.spinnerHour :
                hourChoice = value;
                break;
            case R.id.spinnerDepth :
                depthChoice =value;
                break;
            case R.id.spinnerTypeOfFishing :
                typeofFishingChoice =value ;
                break;
        }
    }
    String toJson()
    {
        StringBuilder str = new StringBuilder();
        if(characterID == Character.FISHER.ordinal())
            str.append(convertJson("fish",fishingChoice))
            .append(",").append(convertJson("fishingM",typeofFishingChoice))
            .append(",");
        else  if(characterID == Character.DIVER.ordinal() || characterID ==Character.SCIENTIST.ordinal())
            str.append(convertJson("fish",fishingChoice))
            .append(",").append(convertJson("depth",depthChoice))
                    .append(",");
        str.append(convertJson("hours",hourChoice));
        return str.toString();
    }

    private String  convertJson(String key, String element)
    {
        return "\""+key+"\":\""+element+"\"";
    }

}
