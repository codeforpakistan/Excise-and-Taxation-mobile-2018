package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shahzaib on 03-Aug-18.
 */

public class Model {

    @SerializedName("model")
    @Expose
    private List<ModelData> model = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<ModelData> getModel() {
        return model;
    }

    public void setModel(List<ModelData> model) {
        this.model = model;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "Model{" +
                "model=" + model +
                ", success=" + success +
                '}';
    }
}
