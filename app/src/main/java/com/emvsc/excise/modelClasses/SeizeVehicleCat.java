package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shahzaib on 10-Aug-18.
 */

public class SeizeVehicleCat {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("seized_vechicle")
    @Expose
    private List<SeizedVechicleCatData> seizedVechicle = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<SeizedVechicleCatData> getSeizedVechicle() {
        return seizedVechicle;
    }

    public void setSeizedVechicle(List<SeizedVechicleCatData> seizedVechicle) {
        this.seizedVechicle = seizedVechicle;
    }

    @Override
    public String toString() {
        return "SeizeVehicleCat{" +
                "success=" + success +
                ", seizedVechicle=" + seizedVechicle +
                '}';
    }
}
