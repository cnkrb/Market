package com.cenkkaraboa.marketynetimuygulamas.Models;


import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Gecmis {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("urun_id")
    @Expose
    private String urunId;
    @SerializedName("sube_id")
    @Expose
    private String subeId;
    @SerializedName("adet")
    @Expose
    private String adet;
    @SerializedName("alis_fiyat")
    @Expose
    private String alisFiyat;
    @SerializedName("turu")
    @Expose
    private String turu;
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

    public String getUrunId() {
        return urunId;
    }

    public void setUrunId(String urunId) {
        this.urunId = urunId;
    }

    public String getSubeId() {
        return subeId;
    }

    public void setSubeId(String subeId) {
        this.subeId = subeId;
    }

    public String getAdet() {
        return adet;
    }

    public void setAdet(String adet) {
        this.adet = adet;
    }

    public String getAlisFiyat() {
        return alisFiyat;
    }

    public void setAlisFiyat(String alisFiyat) {
        this.alisFiyat = alisFiyat;
    }

    public String getTuru() {
        return turu;
    }

    public void setTuru(String turu) {
        this.turu = turu;
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