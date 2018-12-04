package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shahzaib on 10-Aug-18.
 */

public class SeizedVechicleCatData {
    @SerializedName("siezedid")
    @Expose
    private String siezedid;
    @SerializedName("seizedtype")
    @Expose
    private String seizedtype;

    public String getSiezedid() {
        return siezedid;
    }

    public void setSiezedid(String siezedid) {
        this.siezedid = siezedid;
    }

    public String getSeizedtype() {
        return seizedtype;
    }

    public void setSeizedtype(String seizedtype) {
        this.seizedtype = seizedtype;
    }

    @Override
    public String toString() {
        return "SeizedVechicleCatData{" +
                "siezedid='" + siezedid + '\'' +
                ", seizedtype='" + seizedtype + '\'' +
                '}';
    }
}
