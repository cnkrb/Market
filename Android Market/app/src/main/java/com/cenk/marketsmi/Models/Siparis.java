package com.cenk.marketsmi.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Siparis {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("kullanici_adi")
    @Expose
    private String kullaniciAdi;
    @SerializedName("telefon")
    @Expose
    private String telefon;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("adres")
    @Expose
    private String adres;
    @SerializedName("durum_id")
    @Expose
    private String durumId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("kurye_id")
    @Expose
    private Object kuryeId;
    @SerializedName("siparis_aciklama")
    @Expose
    private String siparisAciklama;
    @SerializedName("iptal_aciklama")
    @Expose
    private Object iptalAciklama;
    @SerializedName("sube_id")
    @Expose
    private String subeId;
    @SerializedName("odeme_id")
    @Expose
    private Object odemeId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("get_sepet")
    @Expose
    private List<GetSepet> getSepet = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getDurumId() {
        return durumId;
    }

    public void setDurumId(String durumId) {
        this.durumId = durumId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getKuryeId() {
        return kuryeId;
    }

    public void setKuryeId(Object kuryeId) {
        this.kuryeId = kuryeId;
    }

    public String getSiparisAciklama() {
        return siparisAciklama;
    }

    public void setSiparisAciklama(String siparisAciklama) {
        this.siparisAciklama = siparisAciklama;
    }

    public Object getIptalAciklama() {
        return iptalAciklama;
    }

    public void setIptalAciklama(Object iptalAciklama) {
        this.iptalAciklama = iptalAciklama;
    }

    public String getSubeId() {
        return subeId;
    }

    public void setSubeId(String subeId) {
        this.subeId = subeId;
    }

    public Object getOdemeId() {
        return odemeId;
    }

    public void setOdemeId(Object odemeId) {
        this.odemeId = odemeId;
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

    public List<GetSepet> getGetSepet() {
        return getSepet;
    }

    public void setGetSepet(List<GetSepet> getSepet) {
        this.getSepet = getSepet;
    }

}
