package com.travelapp.travelapp.entity.locations;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "city")
public class City {

    private int id;
    private int countryId;
    private String city;

    public City() {}
    public City(int id, int countryId, String city) {
        this.id = id;
        this.countryId = countryId;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", countryId=" + countryId +
                ", city='" + city + '\'' +
                '}';
    }
}
