package com.travelapp.travelapp.model.locations;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.travelapp.travelapp.model.postedpictures.PicturePlace;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "country")
    private String country;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = CascadeType.ALL)
    private List<City> cities;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    @JsonBackReference(value = "picturePlaces-country")
    private List<PicturePlace> picturePlaces;

    public Country() {}
    public Country(String country) {
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public void addCity(City city){
        if(cities == null){
            cities = new ArrayList<>();
        }

        cities.add(city);
    }

    public List<PicturePlace> getPicturePlaces() {
        return picturePlaces;
    }

    public void setPicturePlaces(List<PicturePlace> picturePlaces) {
        this.picturePlaces = picturePlaces;
    }

    public void addPicturePlace(PicturePlace picturePlace){
        if(picturePlaces == null){
            picturePlaces = new ArrayList<>();
        }

        picturePlaces.add(picturePlace);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", country='" + country + '\'' +
                '}';
    }
}
