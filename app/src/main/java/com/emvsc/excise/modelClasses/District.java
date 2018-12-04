package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shahzaib on 07-Aug-18.
 */

public class District {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("districts")
    @Expose
    private List<DistrictData> districts = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<DistrictData> getDistricts() {
        return districts;
    }

    public void setDistricts(List<DistrictData> districts) {
        this.districts = districts;
    }

    @Override
    public String toString() {
        return "District{" +
                "success=" + success +
                ", districts=" + districts +
                '}';
    }
}
