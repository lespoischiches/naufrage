package com.thomas.bateau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;

public class CarteActivity extends AppCompatActivity {

    private MapView map;
    private Button btnRetour;
    private IMapController mapController;
    private LocationAccess locationAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        setContentView(R.layout.activity_carte);
        btnRetour=findViewById(R.id.carte_btnretour);
        locationAccess=new LocationAccess(this);
        btnRetour.setOnClickListener(click -> {
            finish();
        });
        map=findViewById(R.id.carte_osm);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        double[] location=locationAccess.getLocation();
        if(location == null) {
            location=new double[]{39.5711111, 126.05555555555556};
        }
        GeoPoint startPoint=new GeoPoint(location[0], location[1]);
        mapController=map.getController();
        mapController.setCenter(startPoint);
        mapController.setZoom(18d);

        ArrayList<OverlayItem> items=new ArrayList<>();
        items.add(new OverlayItem("Your location", "You're here !", new GeoPoint(location[0], location[1])));

        ItemizedOverlayWithFocus<OverlayItem> mOverlay=new ItemizedOverlayWithFocus<OverlayItem>(getApplicationContext(), items, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                return true;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        });

        mOverlay.setFocusItemsOnTap(true);
        map.getOverlays().add(mOverlay);


        //Log.d("A", String.valueOf(locationAccess.getLocation()[0]));
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        locationAccess.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        locationAccess.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}