package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shahzaib on 03-Aug-18.
 */

public class Make {
    @SerializedName("make")
    @Expose
    private List<MakeData> make = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<MakeData> getMake() {
        return make;
    }

    public void setMake(List<MakeData> make) {
        this.make = make;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "Make{" +
                "make=" + make +
                ", success=" + success +
                '}';
    }
}


