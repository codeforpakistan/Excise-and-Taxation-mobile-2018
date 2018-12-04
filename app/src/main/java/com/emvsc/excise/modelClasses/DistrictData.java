package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shahzaib on 07-Aug-18.
 */

public class DistrictData {
    @SerializedName("districtid")
    @Expose
    public String districtid;
    @SerializedName("districtname")
    @Expose
    public String districtname;

    public DistrictData(String districtid, String districtname) {
        this.districtid = districtid;
        this.districtname = districtname;
    }

    public String getDistrictid() {
        return districtid;
    }

    public void setDistrictid(String districtid) {
        this.districtid = districtid;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }

    @Override
    public String toString() {
        return "DistrictData{" +
                "districtid='" + districtid + '\'' +
                ", districtname='" + districtname + '\'' +
                '}';
    }
}
