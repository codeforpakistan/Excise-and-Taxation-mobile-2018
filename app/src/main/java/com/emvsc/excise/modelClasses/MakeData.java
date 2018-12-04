package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shahzaib on 03-Aug-18.
 */

public class MakeData {

    @SerializedName("makeid")
    @Expose
    public String makeid;
    @SerializedName("makename")
    @Expose
    public String makename;

    public MakeData(String makeid, String makename) {
        this.makeid = makeid;
        this.makename = makename;
    }

    public String getMakeid() {
        return makeid;
    }

    public void setMakeid(String makeid) {
        this.makeid = makeid;
    }

    public String getMakename() {
        return makename;
    }

    public void setMakename(String makename) {
        this.makename = makename;
    }

    @Override
    public String toString() {
        return "MakeData{" +
                "makeid='" + makeid + '\'' +
                ", makename='" + makename + '\'' +
                '}';
    }
}