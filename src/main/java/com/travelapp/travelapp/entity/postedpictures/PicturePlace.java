package com.travelapp.travelapp.entity.postedpictures;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "picture_place")
public class PicturePlace {

    @Column(name = "file_id")
    private int fileId;

    @Column(name = "country_id")
    private int countryId;

    @Column(name = "city_id")
    private int cityId;

    @Column(name = "commune_id")
    private int communeId;

    @Column(name = "village_id")
    private int villageId;

    @Column(name = "other_id")
    private int otherId;

    public PicturePlace() {}
    public PicturePlace(int fileId, int countryId, int cityId, int communeId, int villageId, int otherId) {
        this.fileId = fileId;
        this.countryId = countryId;
        this.cityId = cityId;
        this.communeId = communeId;
        this.villageId = villageId;
        this.otherId = otherId;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getCommuneId() {
        return communeId;
    }

    public void setCommuneId(int communeId) {
        this.communeId = communeId;
    }

    public int getVillageId() {
        return villageId;
    }

    public void setVillageId(int villageId) {
        this.villageId = villageId;
    }

    public int getOtherId() {
        return otherId;
    }

    public void setOtherId(int otherId) {
        this.otherId = otherId;
    }

    @Override
    public String toString() {
        return "PicturePlace{" +
                "fileId=" + fileId +
                ", countryId=" + countryId +
                ", cityId=" + cityId +
                ", communeId=" + communeId +
                ", villageId=" + villageId +
                ", otherId=" + otherId +
                '}';
    }
}
