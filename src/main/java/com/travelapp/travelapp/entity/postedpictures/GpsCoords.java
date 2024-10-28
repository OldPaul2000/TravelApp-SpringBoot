package com.travelapp.travelapp.entity.postedpictures;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "gps_coords")
public class GpsCoords {

    private String fileId;
    private double latitude;
    private double longitude;

    public GpsCoords() {}
    public GpsCoords(String fileId, double latitude, double longitude) {
        this.fileId = fileId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "GpsCoords{" +
                "fileId='" + fileId + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
