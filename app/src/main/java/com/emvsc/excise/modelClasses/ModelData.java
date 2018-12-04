package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by shahzaib on 03-Aug-18.
 */

public class ModelData {

    @SerializedName("submakeid")
    @Expose
    public String submakeid;
    @SerializedName("make_parent_id")
    @Expose
    public String makeParentId;
    @SerializedName("submakename")
    @Expose
    public String submakename;

    public ModelData(String submakeid, String submakename) {
        this.submakeid = submakeid;
        this.submakename = submakename;
    }

    public String getSubmakeid() {
        return submakeid;
    }

    public void setSubmakeid(String submakeid) {
        this.submakeid = submakeid;
    }

    public String getMakeParentId() {
        return makeParentId;
    }

    public void setMakeParentId(String makeParentId) {
        this.makeParentId = makeParentId;
    }

    public String getSubmakename() {
        return submakename;
    }

    public void setSubmakename(String submakename) {
        this.submakename = submakename;
    }

    @Override
    public String toString() {
        return "ModelData{" +
                "submakeid='" + submakeid + '\'' +
                ", makeParentId='" + makeParentId + '\'' +
                ", submakename='" + submakename + '\'' +
                '}';
    }
}
