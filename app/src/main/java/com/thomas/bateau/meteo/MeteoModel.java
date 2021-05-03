package com.thomas.bateau.meteo;

import com.thomas.bateau.R;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;

public class MeteoModel extends Observable {
    private MeteoLocation meteoLocation =MeteoLocation.METEO_MARINE;
    private final List<Float> listTemperatures = Arrays.asList((float)15.0, (float)10.0);
    private final List<Integer> listHumidity = Arrays.asList(90, 60);
    private final List<MeteoType> listMeteoType = Arrays.asList(MeteoType.CLOUDY, MeteoType.SUNNY);

    private static MeteoModel meteoModel=null;

    public static MeteoModel getInstance() {
        if(meteoModel==null) {
            meteoModel=new MeteoModel();
        }
        return meteoModel;
    }

    private MeteoModel() {
    }

    public float getTemperature() {
        return listTemperatures.get(meteoLocation.getIndex());
    }

    public int getHumidity() {
        return listHumidity.get(meteoLocation.getIndex());
    }

    public MeteoType getMeteoType() {
        return listMeteoType.get(meteoLocation.getIndex());
    }

    public void setTemperature(MeteoLocation meteoLocation, float temperature) {
        listTemperatures.set(meteoLocation.getIndex(), temperature);
        setChanged();
        notifyObservers();
    }

    public void setHumidity(MeteoLocation meteoLocation, int humidity) {
        listHumidity.set(meteoLocation.getIndex(), humidity);
        setChanged();
        notifyObservers();
    }

    public void setMeteoType(MeteoLocation meteoLocation, MeteoType meteoType) {
        listMeteoType.set(meteoLocation.getIndex(), meteoType);
        setChanged();
        notifyObservers();
    }

    public MeteoLocation getMeteoLocation() {
        return meteoLocation;
    }

    public void setMeteoLocation(MeteoLocation meteoLocation) {
        this.meteoLocation = meteoLocation;
        setChanged();
        notifyObservers();
    }

    public enum MeteoLocation {
        METEO_MARINE(0, "Météo marine"),
        METEO_COTIERE(1, "Météo côtière");

        private String label;
        private int index;
        MeteoLocation(int index, String str) {
            label=str;
            this.index=index;
        }

        public String toString() {
            return label;
        }

        public int getIndex() {
            return index;
        }

        public static MeteoLocation[] listTypes={METEO_MARINE, METEO_COTIERE};
    }

    public enum MeteoType {
        CLOUDY("Nuageux", R.drawable.drawable_cloud),
        SUNNY("Ensoleillé", R.drawable.drawable_sun);

        private String label;
        private int icon;
        MeteoType(String str, int icon) {
            this.label=str;
            this.icon=icon;
        }
        public String toString() {
            return label;
        }
        public int getIcon() {
            return icon;
        }
    }
}
