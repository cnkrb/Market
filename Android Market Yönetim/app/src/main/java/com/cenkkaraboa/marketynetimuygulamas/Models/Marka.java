package com.cenkkaraboa.marketynetimuygulamas.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Marka {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("marka_id")
    @Expose
    private String markaId;
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
    @SerializedName("get_marka")
    @Expose
    private GetSubeMarka getMarka;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarkaId() {
        return markaId;
    }

    public void setMarkaId(String markaId) {
        this.markaId = markaId;
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

    public GetSubeMarka getGetMarka() {
        return getMarka;
    }

    public void setGetMarka(GetSubeMarka getMarka) {
        this.getMarka = getMarka;
    }

}
