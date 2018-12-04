package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shahzaib on 14-Aug-18.
 */

public class WhmSeizeInspectorVehicle {
    @SerializedName("seized_data")
    @Expose
    private List<WhmSeizeInspectorVehicleData> seizedData = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<WhmSeizeInspectorVehicleData> getSeizedData() {
        return seizedData;
    }

    public void setSeizedData(List<WhmSeizeInspectorVehicleData> seizedData) {
        this.seizedData = seizedData;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "WhmSeizeInspectorVehicle{" +
                "seizedData=" + seizedData +
                ", success=" + success +
                '}';
    }
}
