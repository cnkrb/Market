package com.cenkkaraboa.marketynetimuygulamas.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Altkatagorus {

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
    private String updatedAt;
    @SerializedName("get_katagori")
    @Expose
    private List<GetKatagorus> getKatagori = null;

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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<GetKatagorus> getGetKatagori() {
        return getKatagori;
    }

    public void setGetKatagori(List<GetKatagorus> getKatagori) {
        this.getKatagori = getKatagori;
    }

}