package com.thomas.bateau;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ReportActivity extends AppCompatActivity {

    private Button btnBack, btnMode;
    private boolean mode = true;
    private Fragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_activity);
        btnBack = findViewById(R.id.btn_retour_signalement);
        btnBack.setOnClickListener(click ->{
            finish();
        });
        frag = new com.thomas.bateau.ReportFragment();
        getFragmentManager().beginTransaction().replace(R.id.test, frag).commit();
        btnMode = findViewById(R.id.mode);
        btnMode.setOnClickListener(click ->{
            if(!mode){
                frag = new com.thomas.bateau.ReportFragment();
                getFragmentManager().beginTransaction().replace(R.id.test, frag).commit();
            }else{
                frag = new ReportFragmentUneMain();
                getFragmentManager().beginTransaction().replace(R.id.test, frag).commit();
            }
            mode = !mode;
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Button buttonS1 = ((ReportFragment)frag).signal1btn;
        buttonS1.setOnClickListener(c -> {
            Intent intent=new Intent(getApplicationContext(), PictureActivity.class);
            startActivity(intent);
        });
    }
}