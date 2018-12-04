package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shahzaib on 10-Aug-18.
 */

public class BodybuildData {

    @SerializedName("bodybuild")
    @Expose
    private String bodybuild;
    @SerializedName("bodybuildname")
    @Expose
    private String bodybuildname;

    public String getBodybuild() {
        return bodybuild;
    }

    public void setBodybuild(String bodybuild) {
        this.bodybuild = bodybuild;
    }

    public String getBodybuildname() {
        return bodybuildname;
    }

    public void setBodybuildname(String bodybuildname) {
        this.bodybuildname = bodybuildname;
    }

    @Override
    public String toString() {
        return "BodybuildData{" +
                "bodybuild='" + bodybuild + '\'' +
                ", bodybuildname='" + bodybuildname + '\'' +
                '}';
    }
}
