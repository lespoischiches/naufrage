package com.thomas.bateau.reportActivity;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;



import com.thomas.bateau.alert.NaufrageActivity;
import com.thomas.bateau.R;
import com.thomas.bateau.spot.FisherSpotActivity;

import java.util.HashMap;
import java.util.function.Function;

public abstract class ReportFragment extends Fragment {


    static HashMap<Integer, Function<View,Intent>> buttonID = new HashMap<>();
    static {
        buttonID.put(R.id.emergency,(view ->{

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:0987654321"));
            return intent;
        }));
        buttonID.put(R.id.naufrage,(view -> new Intent(view.getContext(), NaufrageActivity.class)));
        buttonID.put(R.id.fish_spot,(view -> new Intent(view.getContext(), FisherSpotActivity.class)));
    }
    protected void initUi(View v, int id )
    {
        Button button = v.findViewById(id);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                    startActivity(buttonID.get(id).apply(v));
            }
        });

    }
    protected abstract void init(View v);
}
