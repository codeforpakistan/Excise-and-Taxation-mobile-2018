package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shahzaib on 10-Aug-18.
 */

public class ColorData {
    @SerializedName("colorid")
    @Expose
    private String colorid;
    @SerializedName("colorname")
    @Expose
    private String colorname;

    public String getColorid() {
        return colorid;
    }

    public void setColorid(String colorid) {
        this.colorid = colorid;
    }

    public String getColorname() {
        return colorname;
    }

    public void setColorname(String colorname) {
        this.colorname = colorname;
    }

    @Override
    public String toString() {
        return "ColorData{" +
                "colorid='" + colorid + '\'' +
                ", colorname='" + colorname + '\'' +
                '}';
    }
}
