package com.thomas.bateau;

import android.database.DataSetObserver;
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

import java.util.Arrays;
import java.util.List;

import static com.thomas.bateau.BateauApplication.CHANNEL_1_ID;

public class EvenementAccueilFragment extends Fragment {

    private View fragView;
    private int notificationId=0;

    public EvenementAccueilFragment() {
        //
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragView=inflater.inflate(R.layout.evenement_accueil_fragment, container, false);
        ListView listView=fragView.findViewById(R.id.frag_evenements_list);
        List<String> listItems=Arrays.asList("Hello", "World");
        listView.setAdapter(new ArrayAdapter<String>(fragView.getContext(), android.R.layout.simple_list_item_1, listItems));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendNotificationOnChannel("NOTIF !!", "Hello from notification", CHANNEL_1_ID, NotificationCompat.PRIORITY_DEFAULT);
            }
        });
        return fragView;
    }

    private void sendNotificationOnChannel(String title, String message, String channelId, int priority) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getActivity().getApplicationContext(), channelId).setContentTitle(title).setSmallIcon(R.drawable.cloud_icon).setContentText(message).setPriority(priority).setTimeoutAfter(5000);
        NotificationManagerCompat.from(getActivity()).notify(++notificationId, notification.build());
    }
}
