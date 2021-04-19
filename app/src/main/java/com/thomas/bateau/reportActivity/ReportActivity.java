package com.thomas.bateau.reportActivity;

import android.app.Fragment;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.thomas.bateau.R;
import com.thomas.bateau.reportActivity.reportFragment.ReportFragmentDeuxMain;
import com.thomas.bateau.reportActivity.reportFragment.ReportFragmentUneMain;

public abstract class ReportActivity extends AppCompatActivity {


    protected Button btnBack, btnMode;
    protected boolean mode = true;

    protected void initLayout(int portID, int landID)
    {

        btnMode = findViewById(R.id.mode);


        if(findViewById(portID) != null) {
            Fragment frag = new ReportFragmentDeuxMain();
            getFragmentManager().beginTransaction().replace(R.id.test, frag).commit();
            btnMode.setOnClickListener(click -> {
                if (!mode) getFragmentManager().beginTransaction().replace(R.id.test,  new ReportFragmentDeuxMain()).commit();
                else getFragmentManager().beginTransaction().replace(R.id.test, new ReportFragmentUneMain()).commit();
                mode = !mode;
            });
        }

        if(findViewById(landID)!= null){

            ReportFragmentUneMain fragmentUneMain = new ReportFragmentUneMain();
            getFragmentManager().beginTransaction().replace(R.id.test, fragmentUneMain).commit();
        }
    }
    protected void initReturnBack(int backID)
    {
        btnBack = findViewById(backID);
        btnBack.setOnClickListener(click ->{
            finish();
        });
    }

}
