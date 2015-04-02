package com.itparis.b3.meetsik.beans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bouveti on 11/03/2015.
 */
public class Annonce implements Serializable {

    private int id;
    private String nom;
    private int prix;
    private String date;
    private Auteur auteur;
    private String categorie;

    public Annonce(){
        this.id = 0;
        this.nom ="";
        this.prix = 0;
        this.date = null;
        this.auteur = null;
        this.categorie = null;
    }

    public Annonce(int id, String nom,int prix, String date) throws ParseException{
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
