package com.jin123d.launcher;

import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

public class AppItemBean {

    private Drawable icon;
    private String name;

    private ResolveInfo appInfo;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResolveInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(ResolveInfo appInfo) {
        this.appInfo = appInfo;
    }
}