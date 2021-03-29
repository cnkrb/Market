package com.cenkkaraboa.marketynetimuygulamas.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMarket {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("sube_adi")
    @Expose
    private String subeAdi;
    @SerializedName("adres")
    @Expose
    private Object adres;
    @SerializedName("telefon")
    @Expose
    private Object telefon;
    @SerializedName("gorsel_url")
    @Expose
    private Object gorselUrl;
    @SerializedName("servis")
    @Expose
    private Object servis;
    @SerializedName("slug")
    @Expose
    private String slug;
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

    public String getSubeAdi() {
        return subeAdi;
    }

    public void setSubeAdi(String subeAdi) {
        this.subeAdi = subeAdi;
    }

    public Object getAdres() {
        return adres;
    }

    public void setAdres(Object adres) {
        this.adres = adres;
    }

    public Object getTelefon() {
        return telefon;
    }

    public void setTelefon(Object telefon) {
        this.telefon = telefon;
    }

    public Object getGorselUrl() {
        return gorselUrl;
    }

    public void setGorselUrl(Object gorselUrl) {
        this.gorselUrl = gorselUrl;
    }

    public Object getServis() {
        return servis;
    }

    public void setServis(Object servis) {
        this.servis = servis;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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

