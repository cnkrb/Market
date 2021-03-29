package com.cenk.marketsmi.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoriModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("katagori_id")
    @Expose
    private String katagoriId;
    @SerializedName("sube_id")
    @Expose
    private String subeId;
    @SerializedName("secim")
    @Expose
    private String secim;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("get_katagori")
    @Expose
    private GetKatagori getKatagori;
    @SerializedName("get_subealtkatagori")
    @Expose
    private List<GetSubealtkatagorus> getSubealtkatagori = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKatagoriId() {
        return katagoriId;
    }

    public void setKatagoriId(String katagoriId) {
        this.katagoriId = katagoriId;
    }

    public String getSubeId() {
        return subeId;
    }

    public void setSubeId(String subeId) {
        this.subeId = subeId;
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

    public GetKatagori getGetKatagori() {
        return getKatagori;
    }

    public void setGetKatagori(GetKatagori getKatagori) {
        this.getKatagori = getKatagori;
    }

    public List<GetSubealtkatagorus> getGetSubealtkatagori() {
        return getSubealtkatagori;
    }

    public void setGetSubealtkatagori(List<GetSubealtkatagorus> getSubealtkatagori) {
        this.getSubealtkatagori = getSubealtkatagori;
    }

}