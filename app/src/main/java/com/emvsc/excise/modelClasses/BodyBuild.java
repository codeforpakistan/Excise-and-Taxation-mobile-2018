package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shahzaib on 10-Aug-18.
 */

public class BodyBuild {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("bodybuild")
    @Expose
    private List<BodybuildData> bodybuild = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<BodybuildData> getBodybuild() {
        return bodybuild;
    }

    public void setBodybuild(List<BodybuildData> bodybuild) {
        this.bodybuild = bodybuild;
    }

    @Override
    public String toString() {
        return "BodyBuild{" +
                "success=" + success +
                ", bodybuild=" + bodybuild +
                '}';
    }
}
