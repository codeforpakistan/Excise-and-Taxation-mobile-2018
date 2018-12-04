package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shahzaib on 02-Aug-18.
 */

public class Assecory {

    @SerializedName("accessoryid")
    @Expose
    private String accessoryid;
    @SerializedName("accessoryname")
    @Expose
    private String accessoryname;

    public String getAccessoryid() {
        return accessoryid;
    }

    public void setAccessoryid(String accessoryid) {
        this.accessoryid = accessoryid;
    }

    public String getAccessoryname() {
        return accessoryname;
    }

    public void setAccessoryname(String accessoryname) {
        this.accessoryname = accessoryname;
    }

    @Override
    public String toString() {
        return "Assecory{" +
                "accessoryid='" + accessoryid + '\'' +
                ", accessoryname='" + accessoryname + '\'' +
                '}';
    }
}
