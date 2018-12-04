package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shahzaib on 10-Aug-18.
 */

public class Color {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("colors")
    @Expose
    private List<ColorData> colors = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<ColorData> getColors() {
        return colors;
    }

    public void setColors(List<ColorData> colors) {
        this.colors = colors;
    }

    @Override
    public String toString() {
        return "Color{" +
                "success=" + success +
                ", colors=" + colors +
                '}';
    }
}
