package com.thomas.bateau.coins;

import java.util.Arrays;
import java.util.List;

public class ItemListView {
    private String nom;
    private Integer image;
    private String description,depth,fishingWay,hour,url;


    public ItemListView(String nom, Integer image, String description) {
        this.nom = nom;
        this.image = image;
        this.description = description;
        this.depth =null;
        this.fishingWay=null;
        this.hour = null;
        this.url = null ;
    }
    public ItemListView(String nom, Integer image, String description,String depth, String fishingWay,String hour ,String url) {
        this(nom,image,description);
        this.depth =depth;
        this.fishingWay=fishingWay;
        this.hour = hour;
        this.url = url ;
    }
    public int count(){
        int i,j,k;
        i = fishingWay==null ? 0:1;
        j = depth==null ? 0:1;
        k = hour==null ? 0:1;
        return i+j+k;
    }
    public String getNom() {
        return nom;
    }
    public List<String> getElement()
    {
        return Arrays.asList(hour,depth,fishingWay);
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public String getDepth() {
        return depth;
    }

    public String getFishingWay() {
        return fishingWay;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public void setFishingWay(String fishingWay) {
        this.fishingWay = fishingWay;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getHour() {
        return hour;
    }

    public String getUrl() {
        return url;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
