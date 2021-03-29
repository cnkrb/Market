package com.cenkkaraboa.marketynetimuygulamas.Models;




import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class SubeAltKategori {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("altkatagori_id")
    @Expose
    private String altkatagoriId;
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
    @SerializedName("get_altkatagori")
    @Expose
    private GetAltkatagori getAltkatagori;

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

    public GetAltkatagori getGetAltkatagori() {
        return getAltkatagori;
    }

    public void setGetAltkatagori(GetAltkatagori getAltkatagori) {
        this.getAltkatagori = getAltkatagori;
    }

}