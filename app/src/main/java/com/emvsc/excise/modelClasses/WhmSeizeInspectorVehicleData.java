package com.emvsc.excise.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shahzaib on 14-Aug-18.
 */

public class WhmSeizeInspectorVehicleData {
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
    private String formserialno;
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
    @SerializedName("vehicle_images")
    @Expose
    private List<VehicleImage2> vehicleImages = null;
    @SerializedName("vehicle_accessories")
    @Expose
    private List<VehicleAccessory2> vehicleAccessories = null;

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

    public List<VehicleImage2> getVehicleImages() {
        return vehicleImages;
    }

    public void setVehicleImages(List<VehicleImage2> vehicleImages) {
        this.vehicleImages = vehicleImages;
    }

    public List<VehicleAccessory2> getVehicleAccessories() {
        return vehicleAccessories;
    }

    public void setVehicleAccessories(List<VehicleAccessory2> vehicleAccessories) {
        this.vehicleAccessories = vehicleAccessories;
    }

    @Override
    public String toString() {
        return "WhmSeizeInspectorVehicleData{" +
                "mobilesquadno='" + mobilesquadno + '\'' +
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
                ", vehicleImages=" + vehicleImages +
                ", vehicleAccessories=" + vehicleAccessories +
                '}';
    }
}
class VehicleAccessory2 {

    @SerializedName("accessoryid")
    @Expose
    private String accessoryid;
    @SerializedName("accessoryname")
    @Expose
    private String accessoryname;
    @SerializedName("vechicleaccessoryid")
    @Expose
    private String vechicleaccessoryid;
    @SerializedName("accessories_id")
    @Expose
    private String accessoriesId;
    @SerializedName("vechicle_id")
    @Expose
    private String vechicleId;

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

    public String getVechicleaccessoryid() {
        return vechicleaccessoryid;
    }

    public void setVechicleaccessoryid(String vechicleaccessoryid) {
        this.vechicleaccessoryid = vechicleaccessoryid;
    }

    public String getAccessoriesId() {
        return accessoriesId;
    }

    public void setAccessoriesId(String accessoriesId) {
        this.accessoriesId = accessoriesId;
    }

    public String getVechicleId() {
        return vechicleId;
    }

    public void setVechicleId(String vechicleId) {
        this.vechicleId = vechicleId;
    }

    @Override
    public String toString() {
        return "VehicleAccessory2{" +
                "accessoryid='" + accessoryid + '\'' +
                ", accessoryname='" + accessoryname + '\'' +
                ", vechicleaccessoryid='" + vechicleaccessoryid + '\'' +
                ", accessoriesId='" + accessoriesId + '\'' +
                ", vechicleId='" + vechicleId + '\'' +
                '}';
    }
}
class VehicleImage2 {

    @SerializedName("imageid")
    @Expose
    private String imageid;
    @SerializedName("imagepath")
    @Expose
    private String imagepath;
    @SerializedName("vechile_id")
    @Expose
    private String vechileId;

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
        return "VehicleImage2{" +
                "imageid='" + imageid + '\'' +
                ", imagepath='" + imagepath + '\'' +
                ", vechileId='" + vechileId + '\'' +
                '}';
    }
}