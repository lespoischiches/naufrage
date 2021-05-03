package com.thomas.bateau.coins;

public class ItemListView {
    private String nom;
    private Integer image;
    private String description;

    public ItemListView(String nom, Integer image, String description) {
        this.nom = nom;
        this.image = image;
        this.description = description;
    }

    public String getNom() {
        return nom;
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

    public void setDescription(String description) {
        this.description = description;
    }
}
