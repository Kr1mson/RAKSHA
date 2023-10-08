package com.example.raksha;

public class Location_Helper {

    private double Logitude;
    private double Latitude;

    public Location_Helper(double logitude, double latitude) {
        Logitude = logitude;
        Latitude = latitude;
    }

    public double getLogitude() {
        return Logitude;
    }

    public void setLogitude(double logitude) {
        Logitude = logitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }
}
