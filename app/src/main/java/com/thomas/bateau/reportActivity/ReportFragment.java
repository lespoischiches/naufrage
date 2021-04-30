package com.thomas.bateau.reportActivity;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;



import com.thomas.bateau.alert.NaufrageActivity;
import com.thomas.bateau.R;
import com.thomas.bateau.reportActivity.spotActivity.SpotActivity;
import com.thomas.bateau.alert.AlertMeteoActivity;
import com.thomas.bateau.spot.DiverSpotActivity;
import com.thomas.bateau.spot.FisherSpotActivity;
import com.thomas.bateau.alert.HelpActivity;
import com.thomas.bateau.spot.KitterSpotActivity;
import com.thomas.bateau.alert.RemainsActivity;
import com.thomas.bateau.spot.ScientistSpotActivity;
import com.thomas.bateau.spot.SkipperSpotActivity;

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
        buttonID.put(R.id.scientist_spot,(view -> new Intent(view.getContext(), ScientistSpotActivity.class)));
        buttonID.put(R.id.skipper_spot,(view -> new Intent(view.getContext(), SkipperSpotActivity.class)));
        buttonID.put(R.id.diver_spot,(view -> new Intent(view.getContext(), DiverSpotActivity.class)));
        buttonID.put(R.id.kitter_spot,(view -> new Intent(view.getContext(), KitterSpotActivity.class)));

        buttonID.put(R.id.meteo,(view -> new Intent(view.getContext(), AlertMeteoActivity.class)));
        buttonID.put(R.id.remains,(view -> new Intent(view.getContext(), RemainsActivity.class)));
        buttonID.put(R.id.help,(view -> new Intent(view.getContext(), HelpActivity.class)));

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
