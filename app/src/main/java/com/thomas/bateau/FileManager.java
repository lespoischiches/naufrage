package com.thomas.bateau;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;

public class FileManager {

    public static String filename="data2.json";


    public static void  saveFile( ContextWrapper context, String content)
    {
        File directory = context.getDir("jsonDir", Context.MODE_PRIVATE);
        Log.d("",content);
        Log.d("Path",directory+"/"+filename);
        Log.d("content",content);
        try {

           File file = new File(directory+"/"+filename);
            FileOutputStream out = new FileOutputStream(file,Objects.requireNonNull(directory.listFiles()).length!=0);
            out.write(content.getBytes());
             } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
   public static String saveImage(@NonNull File directory,@NonNull Bitmap content)
    {
        File path = new File(directory, Objects.requireNonNull(directory.listFiles()).length+1+".png");
        try {
            FileOutputStream fos = new FileOutputStream(path);
            content.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path.getPath();
    }

    public static List<JSONObject>  loadFile(String pathFile) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(pathFile+"/"+filename);
        InputStreamReader streamReader = new InputStreamReader(fileInputStream);
        BufferedReader br  = new BufferedReader(streamReader);
        List<JSONObject> jsn = new ArrayList<>();
        String text;
        while((text= br.readLine())!=null) {
            try { jsn.add(new JSONObject(text));
            } catch (JSONException jsonException) { jsonException.printStackTrace(); }
        }
        return jsn;
    }
}
