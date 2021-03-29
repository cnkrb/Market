package com.cenkkaraboa.marketynetimuygulamas.Models;


import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubeKategori {

    @SerializedName("subekatagori")
    @Expose
    private List<Subekatagorus> subekatagori = null;

    public List<Subekatagorus> getSubekatagori() {
        return subekatagori;
    }

    public void setSubekatagori(List<Subekatagorus> subekatagori) {
        this.subekatagori = subekatagori;
    }

}