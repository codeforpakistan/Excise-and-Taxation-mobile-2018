package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shahzaib on 02-Aug-18.
 */

public class Accessories {

    @SerializedName("assecories")
    @Expose
    private List<Assecory> assecories = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<Assecory> getAssecories() {
        return assecories;
    }

    public void setAssecories(List<Assecory> assecories) {
        this.assecories = assecories;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "Accessories{" +
                "assecories=" + assecories +
                ", success=" + success +
                '}';
    }
}
