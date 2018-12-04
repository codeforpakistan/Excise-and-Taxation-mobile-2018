package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shahzaib on 10-Aug-18.
 */

public class WheelData {
    @SerializedName("wheelid")
    @Expose
    private String wheelid;
    @SerializedName("wheelnumber")
    @Expose
    private String wheelnumber;

    public String getWheelid() {
        return wheelid;
    }

    public void setWheelid(String wheelid) {
        this.wheelid = wheelid;
    }

    public String getWheelnumber() {
        return wheelnumber;
    }

    public void setWheelnumber(String wheelnumber) {
        this.wheelnumber = wheelnumber;
    }

    @Override
    public String toString() {
        return "WheelData{" +
                "wheelid='" + wheelid + '\'' +
                ", wheelnumber='" + wheelnumber + '\'' +
                '}';
    }
}
