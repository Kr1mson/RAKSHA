package com.example.raksha;

public class Alert_Helper {
    private String agencyName;
    private String mobile;
    private String alertType;
    private String alertAddress;
    private String otherDetails;

    public Alert_Helper(){

    }
    public Alert_Helper(String agencyName, String mobile, String alertType, String alertAddress, String otherDetails) {
        this.agencyName = agencyName;
        this.mobile = mobile;
        this.alertType = alertType;
        this.alertAddress = alertAddress;
        this.otherDetails = otherDetails;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getAlertAddress() {
        return alertAddress;
    }

    public void setAlertAddress(String alertAddress) {
        this.alertAddress = alertAddress;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }
}

