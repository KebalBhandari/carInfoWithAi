package com.halo.carInfoWithAi.Models;

public class Data {

   private String NoPlate;
   private String CName;
   private String modelNumber;
   private String ccInfo;
    private String colorInfo;
    private String ManufactureDate;
    private String ownerName;
    private String ownerContactInfo;
    private String ownerOccupation;
    private String ownerPhoneNo;
    private String id;

    public Data() {
    }

    public Data(String NoPlate, String CName, String modelNumber, String ccInfo,String colorInfo, String ManufactureDate, String ownerName, String ownerContactInfo,String ownerOccupation, String ownerPhoneNo,String id) {
        this.NoPlate = NoPlate;
        this.CName = CName;
        this.modelNumber = modelNumber;
        this.ccInfo = ccInfo;
        this.colorInfo = colorInfo;
        this.ManufactureDate = ManufactureDate;
        this.ownerName = ownerName;
        this.ownerContactInfo = ownerContactInfo;
        this.ownerOccupation = ownerOccupation;
        this.ownerPhoneNo = ownerPhoneNo;
        this.id = id;
    }

    public String getNoPlate() {
        return NoPlate;
    }

    public void setNoPlate(String NoPlate) {
        this.NoPlate = NoPlate;
    }

    public String getCName() {
        return CName;
    }
    public void setCName(String CName) {
        this.CName = CName;
    }
    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getCcInfo() {
        return ccInfo;
    }
    public void setCcInfo(String ccInfo) {
        this.ccInfo = ccInfo;
    }

    public String getColorInfo() {
        return colorInfo;
    }
    public void setColorInfo(String colorInfo) {
        this.colorInfo = colorInfo;
    }

    public String getManufactureDate() {
        return ManufactureDate;
    }
    public void setManufactureDate(String ManufactureDate) {
        this.ManufactureDate = ManufactureDate;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerContactInfo() {
        return ownerContactInfo;
    }
    public void setOwnerContactInfo(String ownerContactInfo) {
        this.ownerContactInfo = ownerContactInfo;
    }

    public String getOwnerOccupation() {
        return ownerOccupation;
    }
    public void setOwnerOccupation(String ownerOccupation) {
        this.ownerOccupation = ownerOccupation;
    }

    public String getOwnerPhoneNo() {
        return ownerPhoneNo;
    }
    public void setOwnerPhoneNo(String ownerPhoneNo) {
        this.ownerPhoneNo = ownerPhoneNo;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

}
