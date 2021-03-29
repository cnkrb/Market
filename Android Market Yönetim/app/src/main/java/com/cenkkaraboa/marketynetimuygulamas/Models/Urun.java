package com.cenkkaraboa.marketynetimuygulamas.Models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Urun {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("urun_id")
    @Expose
    private String urunId;
    @SerializedName("sube_id")
    @Expose
    private String subeId;
    @SerializedName("stok")
    @Expose
    private String stok;
    @SerializedName("satis_fiyat")
    @Expose
    private Object satisFiyat;
    @SerializedName("urun_turu")
    @Expose
    private String urunTuru;
    @SerializedName("indirim_fiyat")
    @Expose
    private String indirimFiyat;
    @SerializedName("select")
    @Expose
    private String select;
    @SerializedName("secim")
    @Expose
    private String secim;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("get_urun")
    @Expose
    private GetUrun getUrun;

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

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    public Object getSatisFiyat() {
        return satisFiyat;
    }

    public void setSatisFiyat(Object satisFiyat) {
        this.satisFiyat = satisFiyat;
    }

    public String getUrunTuru() {
        return urunTuru;
    }

    public void setUrunTuru(String urunTuru) {
        this.urunTuru = urunTuru;
    }

    public String getIndirimFiyat() {
        return indirimFiyat;
    }

    public void setIndirimFiyat(String indirimFiyat) {
        this.indirimFiyat = indirimFiyat;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public String getSecim() {
        return secim;
    }

    public void setSecim(String secim) {
        this.secim = secim;
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

    public GetUrun getGetUrun() {
        return getUrun;
    }

    public void setGetUrun(GetUrun getUrun) {
        this.getUrun = getUrun;
    }

}