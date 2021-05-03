package com.thomas.bateau;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Objects;

public class FileManager {

    static String filename="data.json";

    public static void  saveFile(@NonNull File directory,String content)
    {
        try {
            FileWriter fileWriter = new FileWriter(directory+"/"+filename,
                    Objects.requireNonNull(directory.listFiles()).length!=0);
            BufferedWriter  bw = new BufferedWriter(fileWriter);
            PrintWriter out = new PrintWriter(bw);
            out.println(content);
           bw.close();
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

}
