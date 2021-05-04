package com.thomas.bateau.coins.searchActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class JsonFilter {

    public static  List<JSONObject>  filterJsonObjects(List<JSONObject> jsonObjects, Filter<String,String,Boolean> filter , String reference, String... filters) {
        return jsonObjects.stream().filter(jsonObject -> Arrays.stream(filters).anyMatch(type-> {
            try { return filter.apply(reference, (String) jsonObject.get(type));
            } catch (JSONException jsonException) { return false; } })
        ).collect(Collectors.toList());

    }
    public static boolean findRegex(String key , String word)
    {
        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(word);
        return matcher.find();
    }

}
