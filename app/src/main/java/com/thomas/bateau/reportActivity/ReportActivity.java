package com.thomas.bateau.reportActivity;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;

import com.thomas.bateau.R;
import com.thomas.bateau.reportActivity.alertActivity.AlertFragmentOneHand;
import com.thomas.bateau.reportActivity.alertActivity.AlertFragmentTwoHand;
import com.thomas.bateau.reportActivity.spotActivity.SpotFragmentTwoHand;

public abstract class ReportActivity extends AppCompatActivity {


    protected Button btnBack, btnMode;
    protected boolean mode = true;

    protected  void initLayout(int portID, int landID)
    {
        btnMode = findViewById(R.id.mode);
    }
    protected void setButtonMode(Fragment fragmentOne, Fragment fragmentTwo)
    {
        getFragmentManager().beginTransaction().replace(R.id.test, fragmentTwo).commit();
        btnMode.setOnClickListener(click -> {
            if (!mode) getFragmentManager().beginTransaction().replace(R.id.test,  fragmentTwo).commit();
            else getFragmentManager().beginTransaction().replace(R.id.test, fragmentOne).commit();
            mode = !mode;});
    }

    protected void initReturnBack(int backID)
    {
        btnBack = findViewById(backID);
        btnBack.setOnClickListener(click ->{
            finish();
        });
    }

}
