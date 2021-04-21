package com.thomas.bateau.evenements;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.thomas.bateau.R;

import java.util.Arrays;
import java.util.List;

import static com.thomas.bateau.BateauApplication.CHANNEL_1_ID;
import static com.thomas.bateau.evenements.Evenement.EVENEMENT;

public class EvenementAccueilFragment extends android.app.Fragment implements IEvenementAdapterListener {

    private View fragView;
    private int notificationId=0;
    private ListView listEvenementsView;
    private EvenementsList evenementsList=new EvenementsList();

    public EvenementAccueilFragment() {
        //
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragView=inflater.inflate(R.layout.evenement_accueil_fragment, container, false);
        listEvenementsView=fragView.findViewById(R.id.frag_evenements_list);
        evenementsList.add(new Evenement("Hello", "world", "https://fr.seaicons.com/wp-content/uploads/2015/06/elephant-icon.png"));
        EvenementsListAdapter evenementsListAdapter=new EvenementsListAdapter(fragView.getContext(), evenementsList);
        listEvenementsView.setAdapter(evenementsListAdapter);
        evenementsListAdapter.addListener(this);
        /*List<String> listItems=Arrays.asList("Hello", "World");
        listView.setAdapter(new ArrayAdapter<String>(fragView.getContext(), android.R.layout.simple_list_item_1, listItems));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendNotificationOnChannel("NOTIF !!", "Hello from notification", CHANNEL_1_ID, NotificationCompat.PRIORITY_DEFAULT);
            }
        });*/
        return fragView;
    }

    // sendNotificationOnChannel("NOTIF !!", "Hello from notification", CHANNEL_1_ID, NotificationCompat.PRIORITY_DEFAULT);
    private void sendNotificationOnChannel(String title, String message, String channelId, int priority) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getActivity().getApplicationContext(), channelId).setContentTitle(title).setSmallIcon(R.drawable.cloud_icon).setContentText(message).setPriority(priority).setTimeoutAfter(5000);
        NotificationManagerCompat.from(getActivity()).notify(++notificationId, notification.build());
    }

    @Override
    public void onClickEvenement(Evenement evenement) {
        Intent intentEvenement=new Intent(fragView.getContext(), EvenementViewActivity.class);
        intentEvenement.putExtra(EVENEMENT, evenement);
        startActivity(intentEvenement);
    }
}
