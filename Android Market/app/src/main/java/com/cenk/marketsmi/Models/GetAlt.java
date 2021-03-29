package com.cenk.marketsmi.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAlt {

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
    private Object gorselUrl;
    @SerializedName("select")
    @Expose
    private String select;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

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

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

}