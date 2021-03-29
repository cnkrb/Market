package com.cenk.marketsmi.Models;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUruns {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("gorsel_url")
    @Expose
    private Object gorselUrl;
    @SerializedName("urun_adi")
    @Expose
    private String urunAdi;
    @SerializedName("marka_id")
    @Expose
    private String markaId;
    @SerializedName("altkatagori_id")
    @Expose
    private String altkatagoriId;
    @SerializedName("select")
    @Expose
    private String select;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("get_marka")
    @Expose
    private List<GetMarka> getMarka = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getGorselUrl() {
        return gorselUrl;
    }

    public void setGorselUrl(Object gorselUrl) {
        this.gorselUrl = gorselUrl;
    }

    public String getUrunAdi() {
        return urunAdi;
    }

    public void setUrunAdi(String urunAdi) {
        this.urunAdi = urunAdi;
    }

    public String getMarkaId() {
        return markaId;
    }

    public void setMarkaId(String markaId) {
        this.markaId = markaId;
    }

    public String getAltkatagoriId() {
        return altkatagoriId;
    }

    public void setAltkatagoriId(String altkatagoriId) {
        this.altkatagoriId = altkatagoriId;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
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

    public List<GetMarka> getGetMarka() {
        return getMarka;
    }

    public void setGetMarka(List<GetMarka> getMarka) {
        this.getMarka = getMarka;
    }

}