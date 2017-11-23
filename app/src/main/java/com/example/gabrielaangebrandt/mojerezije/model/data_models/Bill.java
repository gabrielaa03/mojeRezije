package com.example.gabrielaangebrandt.mojerezije.model.data_models;


import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

import io.realm.RealmObject;

public class Bill extends RealmObject {
    private String tvrtka, datumDospijeca, stanje, iznos, naziv;
    int mjesec;


    public Bill(int mjesec, String datumDospijeca , String naziv, String tvrtka, String iznos , String stanje) {
        this.tvrtka = tvrtka;
        this.datumDospijeca = datumDospijeca;
        this.stanje = stanje;
        this.iznos = iznos;
        this.mjesec = mjesec;
        this.naziv = naziv;
    }

    public Bill() {
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTvrtka() {
        return tvrtka;
    }

    public void setTvrtka(String tvrtka) {
        this.tvrtka = tvrtka;
    }

    public String getDatumDospijeca() {
        return datumDospijeca;
    }

    public void setDatumDospijeca(String datumDospijeca) {
        this.datumDospijeca = datumDospijeca;
    }

    public String getStanje() {
        return stanje;
    }

    public void setStanje(String stanje) {
        this.stanje = stanje;
    }

    public String getIznos() {
        return iznos;
    }

    public void setIznos(String iznos) {
        this.iznos = iznos;
    }

    public int getMjesec() {
        return mjesec;
    }

    public void setMjesec(int mjesec) {
        this.mjesec = mjesec;
    }

}
