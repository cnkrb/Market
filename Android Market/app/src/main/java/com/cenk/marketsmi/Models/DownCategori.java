package com.cenk.marketsmi.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DownCategori {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("altkatagori_adi")
    @Expose
    private String altkatagoriAdi;
    @SerializedName("katagori_id")
    @Expose
    private String katagoriId;
    @SerializedName("gorsel_url")
    @Expose
    private String gorselUrl;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAltkatagoriAdi() {
        return altkatagoriAdi;
    }

    public void setAltkatagoriAdi(String altkatagoriAdi) {
        this.altkatagoriAdi = altkatagoriAdi;
    }

    public String getKatagoriId() {
        return katagoriId;
    }

    public void setKatagoriId(String katagoriId) {
        this.katagoriId = katagoriId;
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

}