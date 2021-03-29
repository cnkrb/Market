package com.cenk.marketsmi.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetSepet {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("siparis_id")
    @Expose
    private String siparisId;
    @SerializedName("sube_urun_id")
    @Expose
    private Object subeUrunId;
    @SerializedName("urun_turu")
    @Expose
    private String urunTuru;
    @SerializedName("urun_adi")
    @Expose
    private String urunAdi;
    @SerializedName("alis_fiyat")
    @Expose
    private Object alisFiyat;
    @SerializedName("satis_fiyat")
    @Expose
    private String satisFiyat;
    @SerializedName("adet")
    @Expose
    private String adet;
    @SerializedName("sube_id")
    @Expose
    private Object subeId;
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

    public String getSiparisId() {
        return siparisId;
    }

    public void setSiparisId(String siparisId) {
        this.siparisId = siparisId;
    }

    public Object getSubeUrunId() {
        return subeUrunId;
    }

    public void setSubeUrunId(Object subeUrunId) {
        this.subeUrunId = subeUrunId;
    }

    public String getUrunTuru() {
        return urunTuru;
    }

    public void setUrunTuru(String urunTuru) {
        this.urunTuru = urunTuru;
    }

    public String getUrunAdi() {
        return urunAdi;
    }

    public void setUrunAdi(String urunAdi) {
        this.urunAdi = urunAdi;
    }

    public Object getAlisFiyat() {
        return alisFiyat;
    }

    public void setAlisFiyat(Object alisFiyat) {
        this.alisFiyat = alisFiyat;
    }

    public String getSatisFiyat() {
        return satisFiyat;
    }

    public void setSatisFiyat(String satisFiyat) {
        this.satisFiyat = satisFiyat;
    }

    public String getAdet() {
        return adet;
    }

    public void setAdet(String adet) {
        this.adet = adet;
    }

    public Object getSubeId() {
        return subeId;
    }

    public void setSubeId(Object subeId) {
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

}
