package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Login {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("logindata")
    @Expose
    private List<Logindata> logindata = null;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("district_id")
    @Expose
    private String districtId;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<Logindata> getLogindata() {
        return logindata;
    }

    public void setLogindata(List<Logindata> logindata) {
        this.logindata = logindata;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    @Override
    public String toString() {
        return "Login{" +
                "success=" + success +
                ", logindata=" + logindata +
                ", role='" + role + '\'' +
                ", districtId='" + districtId + '\'' +
                '}';
    }

    public class Logindata {

        @SerializedName("api_token")
        @Expose
        private String apiToken;
        @SerializedName("userid")
        @Expose
        private String userid;
        @SerializedName("isactive")
        @Expose
        private String isactive;
        @SerializedName("usermobile")
        @Expose
        private String usermobile;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("special_squad")
        @Expose
        private String special_squad;

        public String getSpecial_squad() {
            return special_squad;
        }

        public void setSpecial_squad(String special_squad) {
            this.special_squad = special_squad;
        }

        public String getApiToken() {
            return apiToken;
        }

        public void setApiToken(String apiToken) {
            this.apiToken = apiToken;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getIsactive() {
            return isactive;
        }

        public void setIsactive(String isactive) {
            this.isactive = isactive;
        }

        public String getUsermobile() {
            return usermobile;
        }

        public void setUsermobile(String usermobile) {
            this.usermobile = usermobile;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String toString() {
            return "Logindata{" +
                    "apiToken='" + apiToken + '\'' +
                    ", userid='" + userid + '\'' +
                    ", isactive='" + isactive + '\'' +
                    ", usermobile='" + usermobile + '\'' +
                    ", username='" + username + '\'' +
                    ", special_squad='" + special_squad + '\'' +
                    '}';
        }
    }
}