package com.emvsc.excise.modelClasses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shahzaib on 14-Aug-18.
 */

public class WhmSeizeVehicle {

    @SerializedName("seized_vechicles_data")
    @Expose
    private List<WhmSeizeVehicleData> seizedVechiclesData = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<WhmSeizeVehicleData> getSeizedVechiclesData() {
        return seizedVechiclesData;
    }

    public void setSeizedVechiclesData(List<WhmSeizeVehicleData> seizedVechiclesData) {
        this.seizedVechiclesData = seizedVechiclesData;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "WhmSeizeVehicle{" +
                "seizedVechiclesData=" + seizedVechiclesData +
                ", success=" + success +
                '}';
    }

}
