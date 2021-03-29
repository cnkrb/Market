package com.cenkkaraboa.marketynetimuygulamas.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kurye {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("kurye_adi")
    @Expose
    private String kuryeAdi;
    @SerializedName("sube_id")
    @Expose
    private String subeId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("get_market")
    @Expose
    private GetMarket getMarket;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKuryeAdi() {
        return kuryeAdi;
    }

    public void setKuryeAdi(String kuryeAdi) {
        this.kuryeAdi = kuryeAdi;
    }

    public String getSubeId() {
        return subeId;
    }

    public void setSubeId(String subeId) {
        this.subeId = subeId;
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

    public GetMarket getGetMarket() {
        return getMarket;
    }

    public void setGetMarket(GetMarket getMarket) {
        this.getMarket = getMarket;
    }

}
