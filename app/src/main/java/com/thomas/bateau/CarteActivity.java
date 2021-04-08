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
import android.widget.Button;

import java.util.ArrayList;

public class CarteActivity extends LocationAccessActivity {

    private MapView map;
    private Button btnRetour;
    private IMapController mapController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        setContentView(R.layout.activity_carte);
        btnRetour=findViewById(R.id.carte_btnretour);
        btnRetour.setOnClickListener(click -> {
            finish();
        });
        map=findViewById(R.id.carte_osm);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);


        mapController=map.getController();
        mapController.setZoom(18d);
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

    void addLocationOverlayToMap(Double[] location) {
        GeoPoint startPoint=new GeoPoint(location[0], location[1]);
        mapController.setCenter(startPoint);
        ArrayList<OverlayItem> items=new ArrayList<>();
        items.add(new OverlayItem("Your location", "You're here !", startPoint));

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
    }

    @Override
    public void onNewLocationAvailable() {
        Double[] location=getLocation();
        if(location == null) {
            location=new Double[]{39.5711111, 126.05555555555556};
        }
        addLocationOverlayToMap(location);
    }
}