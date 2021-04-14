package com.thomas.bateau.meteo;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class MeteoModel extends Observable {
    private MeteoType meteoType=MeteoType.METEO_MARINE;
    private Map<MeteoType, Float> listTemperatures=new HashMap<>();

    private static MeteoModel meteoModel=null;

    public static MeteoModel getInstance() {
        if(meteoModel==null) {
            meteoModel=new MeteoModel();
        }
        return meteoModel;
    }

    private MeteoModel() {
        listTemperatures.put(MeteoType.METEO_COTIERE, new Float(15.0));
        listTemperatures.put(MeteoType.METEO_MARINE, new Float(10.0));
    }

    public float getTemperature() {
        return listTemperatures.get(meteoType);
    }

    public void setTemperature(MeteoType meteoType, float temperature) {
        if(!listTemperatures.containsKey(meteoType)) {
            return;
        }
        listTemperatures.put(meteoType, temperature);
        setChanged();
        notifyObservers();
    }

    public MeteoType getMeteoType() {
        return meteoType;
    }

    public void setMeteoType(MeteoType meteoType) {
        this.meteoType=meteoType;
        setChanged();
        notifyObservers();
    }

    public enum MeteoType {
        METEO_MARINE(0, "Météo marine"),
        METEO_COTIERE(1, "Météo côtière");

        private String label;
        private int index;
        MeteoType(int index, String str) {
            label=str;
            this.index=index;
        }

        public String toString() {
            return label;
        }

        public int getIndex() {
            return index;
        }

        public static MeteoType[] listTypes={METEO_MARINE, METEO_COTIERE};
    }
}
