package com.emvsc.excise.modelClasses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shahzaib on 14-Aug-18.
 */

public class WhmSeizeVehicleData implements Parcelable{
    @SerializedName("seize_address")
    @Expose
    private String seize_address;
    @SerializedName("registrationdistrictname")
    @Expose
    private String registrationdistrictname;
    @SerializedName("vehicle_status")
    @Expose
    private String vehicle_status;
    @SerializedName("mobilesquadno")
    @Expose
    private String mobilesquadno;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("districtname")
    @Expose
    private String districtname;
    @SerializedName("seizedtype")
    @Expose
    private String seizedtype;
    @SerializedName("siezeddate")
    @Expose
    private String datesiezeddate;
    @SerializedName("siezedtime")
    @Expose
    private String siezedtime;
    @SerializedName("formserialno")
    @Expose
    public String formserialno;
    @SerializedName("seizedlocationlat")
    @Expose
    private String seizedlocationlat;
    @SerializedName("drivername")
    @Expose
    private String drivername;
    @SerializedName("seizedlocationlong")
    @Expose
    private String seizedlocationlong;
    @SerializedName("chasisno")
    @Expose
    private String chasisno;
    @SerializedName("engineno")
    @Expose
    private String engineno;
    @SerializedName("vechileregistrationno")
    @Expose
    private String vechileregistrationno;
    @SerializedName("makename")
    @Expose
    private String makename;
    @SerializedName("submakename")
    @Expose
    private String submakename;
    @SerializedName("modelyear")
    @Expose
    private String modelyear;
    @SerializedName("vehicletype")
    @Expose
    private String vehicletype;
    @SerializedName("drivercnicno")
    @Expose
    private String drivercnicno;
    @SerializedName("drivermobileno")
    @Expose
    private String drivermobileno;
    @SerializedName("driveraddress")
    @Expose
    private String driveraddress;
    @SerializedName("vechileownername")
    @Expose
    private String vechileownername;
    @SerializedName("vechileownercnic")
    @Expose
    private String vechileownercnic;
    @SerializedName("vechileownermobileno")
    @Expose
    private String vechileownermobileno;
    @SerializedName("vechileid")
    @Expose
    private String vechileid;
    @SerializedName("bodybuildname")
    @Expose
    private String bodybuildname;
    @SerializedName("colorname")
    @Expose
    private String colorname;
    @SerializedName("transmission")
    @Expose
    private String transmission;
    @SerializedName("assembely")
    @Expose
    private String assembely;
    @SerializedName("wheelnumber")
    @Expose
    private String wheelnumber;
    @SerializedName("enginetype")
    @Expose
    private String enginetype;
    @SerializedName("vehicleengine_capcaity")
    @Expose
    private String vehicleengineCapcaity;
    @SerializedName("mileage")
    @Expose
    private String mileage;
    @SerializedName("vechicledescription")
    @Expose
    private String vechicledescription;
    @SerializedName("approved_date")
    @Expose
    private String approvedDate;
    @SerializedName("approved_time")
    @Expose
    private String approvedTime;

    public WhmSeizeVehicleData(String vehicle_status, String mobilesquadno, String username, String districtname, String seizedtype, String datesiezeddate, String siezedtime, String formserialno, String seizedlocationlat, String drivername, String seizedlocationlong, String chasisno, String engineno, String vechileregistrationno, String makename, String submakename, String modelyear, String vehicletype, String drivercnicno, String drivermobileno, String driveraddress, String vechileownername, String vechileownercnic, String vechileownermobileno, String vechileid, String bodybuildname, String colorname, String transmission, String assembely, String wheelnumber, String enginetype, String vehicleengineCapcaity, String mileage, String vechicledescription, String approvedDate, String approvedTime) {
        this.vehicle_status = vehicle_status;
        this.mobilesquadno = mobilesquadno;
        this.username = username;
        this.districtname = districtname;
        this.seizedtype = seizedtype;
        this.datesiezeddate = datesiezeddate;
        this.siezedtime = siezedtime;
        this.formserialno = formserialno;
        this.seizedlocationlat = seizedlocationlat;
        this.drivername = drivername;
        this.seizedlocationlong = seizedlocationlong;
        this.chasisno = chasisno;
        this.engineno = engineno;
        this.vechileregistrationno = vechileregistrationno;
        this.makename = makename;
        this.submakename = submakename;
        this.modelyear = modelyear;
        this.vehicletype = vehicletype;
        this.drivercnicno = drivercnicno;
        this.drivermobileno = drivermobileno;
        this.driveraddress = driveraddress;
        this.vechileownername = vechileownername;
        this.vechileownercnic = vechileownercnic;
        this.vechileownermobileno = vechileownermobileno;
        this.vechileid = vechileid;
        this.bodybuildname = bodybuildname;
        this.colorname = colorname;
        this.transmission = transmission;
        this.assembely = assembely;
        this.wheelnumber = wheelnumber;
        this.enginetype = enginetype;
        this.vehicleengineCapcaity = vehicleengineCapcaity;
        this.mileage = mileage;
        this.vechicledescription = vechicledescription;
        this.approvedDate = approvedDate;
        this.approvedTime = approvedTime;
    }

    @SerializedName("vehicle_images")
    @Expose

    private List<VehicleImage> vehicleImages = null;
    @SerializedName("vehicle_accessories")
    @Expose
    private List<VehicleAccessory> vehicleAccessories = null;

    protected WhmSeizeVehicleData(Parcel in) {
        seize_address = in.readString();
        registrationdistrictname = in.readString();
        mobilesquadno = in.readString();
        username = in.readString();
        districtname = in.readString();
        seizedtype = in.readString();
        datesiezeddate = in.readString();
        siezedtime = in.readString();
        formserialno = in.readString();
        seizedlocationlat = in.readString();
        drivername = in.readString();
        seizedlocationlong = in.readString();
        chasisno = in.readString();
        engineno = in.readString();
        vechileregistrationno = in.readString();
        makename = in.readString();
        submakename = in.readString();
        modelyear = in.readString();
        vehicletype = in.readString();
        drivercnicno = in.readString();
        drivermobileno = in.readString();
        driveraddress = in.readString();
        vechileownername = in.readString();
        vechileownercnic = in.readString();
        vechileownermobileno = in.readString();
        vechileid = in.readString();
        bodybuildname = in.readString();
        colorname = in.readString();
        transmission = in.readString();
        assembely = in.readString();
        wheelnumber = in.readString();
        enginetype = in.readString();
        vehicleengineCapcaity = in.readString();
        mileage = in.readString();
        vechicledescription = in.readString();
        approvedDate = in.readString();
        approvedTime = in.readString();
    }

    public static final Creator<WhmSeizeVehicleData> CREATOR = new Creator<WhmSeizeVehicleData>() {
        @Override
        public WhmSeizeVehicleData createFromParcel(Parcel in) {
            return new WhmSeizeVehicleData(in);
        }

        @Override
        public WhmSeizeVehicleData[] newArray(int size) {
            return new WhmSeizeVehicleData[size];
        }
    };

    public String getSeize_address() {
        return seize_address;
    }

    public void setSeize_address(String seize_address) {
        this.seize_address = seize_address;
    }

    public String getRegistrationdistrictname() {
        return registrationdistrictname;
    }

    public void setRegistrationdistrictname(String registrationdistrictname) {
        this.registrationdistrictname = registrationdistrictname;
    }

    public String getVehicle_status() {
        return vehicle_status;
    }

    public void setVehicle_status(String vehicle_status) {
        this.vehicle_status = vehicle_status;
    }

    public String getMobilesquadno() {
        return mobilesquadno;
    }
    public void setMobilesquadno(String mobilesquadno) {
        this.mobilesquadno = mobilesquadno;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getDistrictname() {
        return districtname;
    }
    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }
    public String getSeizedtype() {
        return seizedtype;
    }
    public void setSeizedtype(String seizedtype) {
        this.seizedtype = seizedtype;
    }
    public String getDatesiezeddate() {
        return datesiezeddate;
    }
    public void setDatesiezeddate(String datesiezeddate) {
        this.datesiezeddate = datesiezeddate;
    }
    public String getSiezedtime() {
        return siezedtime;
    }
    public void setSiezedtime(String siezedtime) {
        this.siezedtime = siezedtime;
    }
    public String getFormserialno() {
        return formserialno;
    }
    public void setFormserialno(String formserialno) {
        this.formserialno = formserialno;
    }
    public String getSeizedlocationlat() {
        return seizedlocationlat;
    }
    public void setSeizedlocationlat(String seizedlocationlat) {
        this.seizedlocationlat = seizedlocationlat;
    }
    public String getDrivername() {
        return drivername;
    }
    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }
    public String getSeizedlocationlong() {
        return seizedlocationlong;
    }
    public void setSeizedlocationlong(String seizedlocationlong) {
        this.seizedlocationlong = seizedlocationlong;
    }
    public String getChasisno() {
        return chasisno;
    }
    public void setChasisno(String chasisno) {
        this.chasisno = chasisno;
    }
    public String getEngineno() {
        return engineno;
    }
    public void setEngineno(String engineno) {
        this.engineno = engineno;
    }
    public String getVechileregistrationno() {
        return vechileregistrationno;
    }
    public void setVechileregistrationno(String vechileregistrationno) {
        this.vechileregistrationno = vechileregistrationno;
    }
    public String getMakename() {
        return makename;
    }
    public void setMakename(String makename) {
        this.makename = makename;
    }
    public String getSubmakename() {
        return submakename;
    }
    public void setSubmakename(String submakename) {
        this.submakename = submakename;
    }
    public String getModelyear() {
        return modelyear;
    }
    public void setModelyear(String modelyear) {
        this.modelyear = modelyear;
    }
    public String getVehicletype() {
        return vehicletype;
    }
    public void setVehicletype(String vehicletype) {
        this.vehicletype = vehicletype;
    }
    public String getDrivercnicno() {
        return drivercnicno;
    }
    public void setDrivercnicno(String drivercnicno) {
        this.drivercnicno = drivercnicno;
    }
    public String getDrivermobileno() {
        return drivermobileno;
    }
    public void setDrivermobileno(String drivermobileno) {
        this.drivermobileno = drivermobileno;
    }
    public String getDriveraddress() {
        return driveraddress;
    }
    public void setDriveraddress(String driveraddress) {
        this.driveraddress = driveraddress;
    }
    public String getVechileownername() {
        return vechileownername;
    }
    public void setVechileownername(String vechileownername) {
        this.vechileownername = vechileownername;
    }
    public String getVechileownercnic() {
        return vechileownercnic;
    }
    public void setVechileownercnic(String vechileownercnic) {
        this.vechileownercnic = vechileownercnic;
    }
    public String getVechileownermobileno() {
        return vechileownermobileno;
    }
    public void setVechileownermobileno(String vechileownermobileno) {
        this.vechileownermobileno = vechileownermobileno;
    }
    public String getVechileid() {
        return vechileid;
    }
    public void setVechileid(String vechileid) {
        this.vechileid = vechileid;
    }
    public String getBodybuildname() {
        return bodybuildname;
    }
    public void setBodybuildname(String bodybuildname) {
        this.bodybuildname = bodybuildname;
    }
    public String getColorname() {
        return colorname;
    }
    public void setColorname(String colorname) {
        this.colorname = colorname;
    }
    public String getTransmission() {
        return transmission;
    }
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
    public String getAssembely() {
        return assembely;
    }
    public void setAssembely(String assembely) {
        this.assembely = assembely;
    }
    public String getWheelnumber() {
        return wheelnumber;
    }
    public void setWheelnumber(String wheelnumber) {
        this.wheelnumber = wheelnumber;
    }
    public String getEnginetype() {
        return enginetype;
    }
    public void setEnginetype(String enginetype) {
        this.enginetype = enginetype;
    }
    public String getVehicleengineCapcaity() {
        return vehicleengineCapcaity;
    }
    public void setVehicleengineCapcaity(String vehicleengineCapcaity) {
        this.vehicleengineCapcaity = vehicleengineCapcaity;
    }
    public String getMileage() {
        return mileage;
    }
    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
    public String getVechicledescription() {
        return vechicledescription;
    }
    public void setVechicledescription(String vechicledescription) {
        this.vechicledescription = vechicledescription;
    }
    public String getApprovedDate() {
        return approvedDate;
    }
    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }
    public String getApprovedTime() {
        return approvedTime;
    }
    public void setApprovedTime(String approvedTime) {
        this.approvedTime = approvedTime;
    }
    public List<VehicleImage> getVehicleImages() {
        return vehicleImages;
    }
    public void setVehicleImages(List<VehicleImage> vehicleImages) {
        this.vehicleImages = vehicleImages;
    }
    public List<VehicleAccessory> getVehicleAccessories() {
        return vehicleAccessories;
    }
    public void setVehicleAccessories(List<VehicleAccessory> vehicleAccessories) {
        this.vehicleAccessories = vehicleAccessories;
    }

    @Override
    public String toString() {
        return "WhmSeizeVehicleData{" +
                "vehicle_status='" + vehicle_status + '\'' +
                ", mobilesquadno='" + mobilesquadno + '\'' +
                ", username='" + username + '\'' +
                ", districtname='" + districtname + '\'' +
                ", seizedtype='" + seizedtype + '\'' +
                ", datesiezeddate='" + datesiezeddate + '\'' +
                ", siezedtime='" + siezedtime + '\'' +
                ", formserialno='" + formserialno + '\'' +
                ", seizedlocationlat='" + seizedlocationlat + '\'' +
                ", drivername='" + drivername + '\'' +
                ", seizedlocationlong='" + seizedlocationlong + '\'' +
                ", chasisno='" + chasisno + '\'' +
                ", engineno='" + engineno + '\'' +
                ", vechileregistrationno='" + vechileregistrationno + '\'' +
                ", makename='" + makename + '\'' +
                ", submakename='" + submakename + '\'' +
                ", modelyear='" + modelyear + '\'' +
                ", vehicletype='" + vehicletype + '\'' +
                ", drivercnicno='" + drivercnicno + '\'' +
                ", drivermobileno='" + drivermobileno + '\'' +
                ", driveraddress='" + driveraddress + '\'' +
                ", vechileownername='" + vechileownername + '\'' +
                ", vechileownercnic='" + vechileownercnic + '\'' +
                ", vechileownermobileno='" + vechileownermobileno + '\'' +
                ", vechileid='" + vechileid + '\'' +
                ", bodybuildname='" + bodybuildname + '\'' +
                ", colorname='" + colorname + '\'' +
                ", transmission='" + transmission + '\'' +
                ", assembely='" + assembely + '\'' +
                ", wheelnumber='" + wheelnumber + '\'' +
                ", enginetype='" + enginetype + '\'' +
                ", vehicleengineCapcaity='" + vehicleengineCapcaity + '\'' +
                ", mileage='" + mileage + '\'' +
                ", vechicledescription='" + vechicledescription + '\'' +
                ", approvedDate='" + approvedDate + '\'' +
                ", approvedTime='" + approvedTime + '\'' +
                ", vehicleImages=" + vehicleImages +
                ", vehicleAccessories=" + vehicleAccessories +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mobilesquadno);
        parcel.writeString(username);
        parcel.writeString(districtname);
        parcel.writeString(seizedtype);
        parcel.writeString(datesiezeddate);
        parcel.writeString(siezedtime);
        parcel.writeString(formserialno);
        parcel.writeString(seizedlocationlat);
        parcel.writeString(drivername);
        parcel.writeString(seizedlocationlong);
        parcel.writeString(chasisno);
        parcel.writeString(engineno);
        parcel.writeString(vechileregistrationno);
        parcel.writeString(makename);
        parcel.writeString(submakename);
        parcel.writeString(modelyear);
        parcel.writeString(vehicletype);
        parcel.writeString(drivercnicno);
        parcel.writeString(drivermobileno);
        parcel.writeString(driveraddress);
        parcel.writeString(vechileownername);
        parcel.writeString(vechileownercnic);
        parcel.writeString(vechileownermobileno);
        parcel.writeString(vechileid);
        parcel.writeString(bodybuildname);
        parcel.writeString(colorname);
        parcel.writeString(transmission);
        parcel.writeString(assembely);
        parcel.writeString(wheelnumber);
        parcel.writeString(enginetype);
        parcel.writeString(vehicleengineCapcaity);
        parcel.writeString(mileage);
        parcel.writeString(vechicledescription);
        parcel.writeString(approvedDate);
        parcel.writeString(approvedTime);
    }

    public static class VehicleAccessory implements Parcelable {

        @SerializedName("accessoryid")
        @Expose
        private String accessoryid;
        @SerializedName("accessoryname")
        @Expose
        private String accessoryname;

        public VehicleAccessory(String accessoryid, String accessoryname) {
            this.accessoryid = accessoryid;
            this.accessoryname = accessoryname;
        }

        protected VehicleAccessory(Parcel in) {
            accessoryid = in.readString();
            accessoryname = in.readString();
        }

        public static final Creator<VehicleAccessory> CREATOR = new Creator<VehicleAccessory>() {
            @Override
            public VehicleAccessory createFromParcel(Parcel in) {
                return new VehicleAccessory(in);
            }

            @Override
            public VehicleAccessory[] newArray(int size) {
                return new VehicleAccessory[size];
            }
        };

        public String getAccessoryid() {
            return accessoryid;
        }

        public void setAccessoryid(String accessoryid) {
            this.accessoryid = accessoryid;
        }

        public String getAccessoryname() {
            return accessoryname;
        }

        public void setAccessoryname(String accessoryname) {
            this.accessoryname = accessoryname;
        }

        @Override
        public String toString() {
            return "VehicleAccessory{" +
                    "accessoryid='" + accessoryid + '\'' +
                    ", accessoryname='" + accessoryname + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(accessoryid);
            parcel.writeString(accessoryname);

        }
    }

    public static class VehicleImage implements Parcelable{

        @SerializedName("imageid")
        @Expose
        private String imageid;
        @SerializedName("imagepath")
        @Expose
        private String imagepath;
        @SerializedName("vechile_id")
        @Expose
        private String vechileId;

        public VehicleImage(String imageid, String imagepath, String vechileId) {
            this.imageid = imageid;
            this.imagepath = imagepath;
            this.vechileId = vechileId;
        }

        protected VehicleImage(Parcel in) {
            imageid = in.readString();
            imagepath = in.readString();
            vechileId = in.readString();
        }

        public static final Creator<VehicleImage> CREATOR = new Creator<VehicleImage>() {
            @Override
            public VehicleImage createFromParcel(Parcel in) {
                return new VehicleImage(in);
            }

            @Override
            public VehicleImage[] newArray(int size) {
                return new VehicleImage[size];
            }
        };

        public String getImageid() {
            return imageid;
        }

        public void setImageid(String imageid) {
            this.imageid = imageid;
        }

        public String getImagepath() {
            return imagepath;
        }

        public void setImagepath(String imagepath) {
            this.imagepath = imagepath;
        }

        public String getVechileId() {
            return vechileId;
        }

        public void setVechileId(String vechileId) {
            this.vechileId = vechileId;
        }

        @Override
        public String toString() {
            return "VehicleImage{" +
                    "imageid='" + imageid + '\'' +
                    ", imagepath='" + imagepath + '\'' +
                    ", vechileId='" + vechileId + '\'' +
                    '}';
        }

       @Override
       public int describeContents() {
           return 0;
       }

       @Override
       public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(imageid);
            parcel.writeString(imagepath);
            parcel.writeString(vechileId);
       }
   }
}
