package com.travelapp.travelapp.entity.locations;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "commune")
public class Commune {

    private int id;
    private int cityId;
    private String commune;

    public Commune() {}
    public Commune(int id, int cityId, String commune) {
        this.id = id;
        this.cityId = cityId;
        this.commune = commune;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    @Override
    public String toString() {
        return "Commune{" +
                "id=" + id +
                ", cityId=" + cityId +
                ", commune='" + commune + '\'' +
                '}';
    }
}
