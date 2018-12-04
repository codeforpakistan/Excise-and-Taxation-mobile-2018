package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shahzaib on 10-Aug-18.
 */

public class ModelYear {
    @SerializedName("model_year")
    @Expose
    private List<ModelYearData> modelYear = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<ModelYearData> getModelYear() {
        return modelYear;
    }

    public void setModelYear(List<ModelYearData> modelYear) {
        this.modelYear = modelYear;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "ModelYear{" +
                "modelYear=" + modelYear +
                ", success=" + success +
                '}';
    }
}
