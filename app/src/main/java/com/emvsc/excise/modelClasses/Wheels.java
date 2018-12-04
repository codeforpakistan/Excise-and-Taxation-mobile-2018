package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shahzaib on 10-Aug-18.
 */

public class Wheels {
    @SerializedName("wheel_number")
    @Expose
    private List<WheelData> wheelNumber = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<WheelData> getWheelNumber() {
        return wheelNumber;
    }

    public void setWheelNumber(List<WheelData> wheelNumber) {
        this.wheelNumber = wheelNumber;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "Wheels{" +
                "wheelNumber=" + wheelNumber +
                ", success=" + success +
                '}';
    }
}
