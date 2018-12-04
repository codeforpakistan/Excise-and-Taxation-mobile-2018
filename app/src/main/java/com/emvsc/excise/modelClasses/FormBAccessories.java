package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FormBAccessories {
    @SerializedName("accessories_checked")
    @Expose
    private List<AccessoriesChecked> accessoriesChecked = null;
    @SerializedName("accessories_unchecked")
    @Expose
    private List<AccessoriesUnchecked> accessoriesUnchecked = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<AccessoriesChecked> getAccessoriesChecked() {
        return accessoriesChecked;
    }

    public void setAccessoriesChecked(List<AccessoriesChecked> accessoriesChecked) {
        this.accessoriesChecked = accessoriesChecked;
    }

    public List<AccessoriesUnchecked> getAccessoriesUnchecked() {
        return accessoriesUnchecked;
    }

    public void setAccessoriesUnchecked(List<AccessoriesUnchecked> accessoriesUnchecked) {
        this.accessoriesUnchecked = accessoriesUnchecked;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "FormBAccessories{" +
                "accessoriesChecked=" + accessoriesChecked +
                ", accessoriesUnchecked=" + accessoriesUnchecked +
                ", success=" + success +
                '}';
    }


    public class AccessoriesUnchecked {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("status")
        @Expose
        private Integer status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "AccessoriesUnchecked{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", status=" + status +
                    '}';
        }
    }
    public class AccessoriesChecked {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("status")
        @Expose
        private Integer status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "AccessoriesChecked{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", status=" + status +
                    '}';
        }
    }

}
