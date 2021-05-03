package com.thomas.bateau.reportActivity;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;


import com.thomas.bateau.Character;
import com.thomas.bateau.alert.NaufrageActivity;
import com.thomas.bateau.R;
import com.thomas.bateau.alert.AlertMeteoActivity;
import com.thomas.bateau.spot.CommonSpotActivity;
import com.thomas.bateau.alert.HelpActivity;
import com.thomas.bateau.alert.RemainsActivity;

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

        buttonID.put(R.id.fish_spot,(view ->
                new Intent(view.getContext(), CommonSpotActivity.class).putExtra("ID",Character.FISHER.ordinal())));
        buttonID.put(R.id.scientist_spot,(view ->
                (new Intent(view.getContext(), CommonSpotActivity.class)).putExtra("ID", Character.SCIENTIST.ordinal())));
        buttonID.put(R.id.skipper_spot,(view ->
                new Intent(view.getContext(), CommonSpotActivity.class).putExtra("ID", Character.SKIPPER.ordinal())));
        buttonID.put(R.id.diver_spot,(view ->
                new Intent(view.getContext(), CommonSpotActivity.class).putExtra("ID", Character.DIVER.ordinal())));
        buttonID.put(R.id.kitter_spot,(view ->
                new Intent(view.getContext(), CommonSpotActivity.class).putExtra("ID", Character.KITTER.ordinal())));

        buttonID.put(R.id.meteo,(view -> new Intent(view.getContext(), AlertMeteoActivity.class)));
        buttonID.put(R.id.remains,(view -> new Intent(view.getContext(), RemainsActivity.class)));
        buttonID.put(R.id.help,(view -> new Intent(view.getContext(), HelpActivity.class)));
    }

    protected void initUi(View v, int id )
    {
        Button button = v.findViewById(id);
        button.setOnClickListener(click-> {

            startActivity( buttonID.get(id).apply(v));
        });

    }
    protected abstract void init(View v);
}
