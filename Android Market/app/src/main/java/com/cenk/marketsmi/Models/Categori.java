package com.cenk.marketsmi.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Categori {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("katagori_adi")
    @Expose
    private String katagoriAdi;
    @SerializedName("gorsel_url")
    @Expose
    private String gorselUrl;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("get_altkatagori")
    @Expose
    private List<DownCategori> getAltkatagori = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKatagoriAdi() {
        return katagoriAdi;
    }

    public void setKatagoriAdi(String katagoriAdi) {
        this.katagoriAdi = katagoriAdi;
    }

    public String getGorselUrl() {
        return gorselUrl;
    }

    public void setGorselUrl(String gorselUrl) {
        this.gorselUrl = gorselUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<DownCategori> getGetAltkatagori() {
        return getAltkatagori;
    }

    public void setGetAltkatagori(List<DownCategori> getAltkatagori) {
        this.getAltkatagori = getAltkatagori;
    }


}