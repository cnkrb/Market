package com.cenkkaraboa.marketynetimuygulamas.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubeSiparis {

    @SerializedName("siparis")
    @Expose
    private List<AdminSiparis> siparis = null;

    public List<AdminSiparis> getSiparis() {
        return siparis;
    }

    public void setSiparis(List<AdminSiparis> siparis) {
        this.siparis = siparis;
    }

}
