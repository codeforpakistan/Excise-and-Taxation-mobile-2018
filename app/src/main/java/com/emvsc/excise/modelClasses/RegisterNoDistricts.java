package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegisterNoDistricts {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("registation_district")
    @Expose
    private List<RegistationDistrict> registationDistrict = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<RegistationDistrict> getRegistationDistrict() {
        return registationDistrict;
    }

    public void setRegistationDistrict(List<RegistationDistrict> registationDistrict) {
        this.registationDistrict = registationDistrict;
    }

    @Override
    public String toString() {
        return "RegisterNoDistricts{" +
                "success=" + success +
                ", registationDistrict=" + registationDistrict +
                '}';
    }


    public static class RegistationDistrict {

        @SerializedName("registrationdistrictid")
        @Expose
        public String registrationdistrictid;
        @SerializedName("registrationdistrictname")
        @Expose
        public String registrationdistrictname;

        public RegistationDistrict(String registrationdistrictid, String registrationdistrictname) {
            this.registrationdistrictid = registrationdistrictid;
            this.registrationdistrictname = registrationdistrictname;
        }

        public String getRegistrationdistrictid() {
            return registrationdistrictid;
        }

        public void setRegistrationdistrictid(String registrationdistrictid) {
            this.registrationdistrictid = registrationdistrictid;
        }

        public String getRegistrationdistrictname() {
            return registrationdistrictname;
        }

        public void setRegistrationdistrictname(String registrationdistrictname) {
            this.registrationdistrictname = registrationdistrictname;
        }

        @Override
        public String toString() {
            return "RegistationDistrict{" +
                    "registrationdistrictid='" + registrationdistrictid + '\'' +
                    ", registrationdistrictname='" + registrationdistrictname + '\'' +
                    '}';
        }
    }


}
