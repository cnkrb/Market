package com.cenk.marketsmi.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Marka {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("altkatagori_id")
    @Expose
    private String altkatagoriId;
    @SerializedName("marka_adi")
    @Expose
    private String markaAdi;
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

    public String getAltkatagoriId() {
        return altkatagoriId;
    }

    public void setAltkatagoriId(String altkatagoriId) {
        this.altkatagoriId = altkatagoriId;
    }

    public String getMarkaAdi() {
        return markaAdi;
    }

    public void setMarkaAdi(String markaAdi) {
        this.markaAdi = markaAdi;
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