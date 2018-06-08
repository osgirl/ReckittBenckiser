package com.cpm.GetterSetter;

/**
 * Created by yadavendras on 15-12-2015.
 */
public class NavMenuItemGetterSetter {

    String iconName = "";
    int iconImg = -1; // menu icon resource id

    public String getIconName() {
        return iconName;
    }
    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
    public int getIconImg() {
        return iconImg;
    }
    public void setIconImg(int iconImg) {
        this.iconImg = iconImg;
    }
}
