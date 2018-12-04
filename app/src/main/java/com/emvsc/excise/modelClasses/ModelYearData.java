package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shahzaib on 10-Aug-18.
 */

public class ModelYearData {
    @SerializedName("modelid")
    @Expose
    private String modelid;
    @SerializedName("modelyear")
    @Expose
    private String modelyear;

    public String getModelid() {
        return modelid;
    }

    public void setModelid(String modelid) {
        this.modelid = modelid;
    }

    public String getModelyear() {
        return modelyear;
    }

    public void setModelyear(String modelyear) {
        this.modelyear = modelyear;
    }

    @Override
    public String toString() {
        return "ModelYearData{" +
                "modelid='" + modelid + '\'' +
                ", modelyear='" + modelyear + '\'' +
                '}';
    }
}
