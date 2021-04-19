package com.thomas.bateau.reportActivity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import android.app.Fragment;

import com.thomas.bateau.NaufrageActivity;
import com.thomas.bateau.R;

public class ReportFragment extends Fragment {

    protected void initUIEmergency(View v){
        Button emergency =v.findViewById(R.id.emergency);
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0987654321"));
                startActivity(intent);

            }
        });
    }

    protected void initUINaufrage(View v){
        Button emergency =v.findViewById(R.id.naufrage);
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), NaufrageActivity.class);
                startActivity(intent);


            }
        });
    }
}
