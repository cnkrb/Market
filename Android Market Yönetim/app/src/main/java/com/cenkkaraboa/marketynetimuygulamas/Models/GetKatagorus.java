package com.cenkkaraboa.marketynetimuygulamas.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetKatagorus {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("katagori_adi")
    @Expose
    private String katagoriAdi;
    @SerializedName("gorsel_url")
    @Expose
    private Object gorselUrl;
    @SerializedName("select")
    @Expose
    private String select;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

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

    public Object getGorselUrl() {
        return gorselUrl;
    }

    public void setGorselUrl(Object gorselUrl) {
        this.gorselUrl = gorselUrl;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
