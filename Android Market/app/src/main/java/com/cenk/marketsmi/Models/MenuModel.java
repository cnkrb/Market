package com.cenk.marketsmi.Models;

import android.graphics.drawable.Drawable;


public class MenuModel {

    public String menuName, url;
    public boolean hasChildren, isGroup;
    public String image;

    public MenuModel(String menuName, boolean isGroup, boolean hasChildren, String url, String image) {

        this.menuName = menuName;
        this.url = url;
        this.isGroup = isGroup;
        this.hasChildren = hasChildren;
        this.image=image;
    }
}
