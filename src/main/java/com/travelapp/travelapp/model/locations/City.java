package com.travelapp.travelapp.model.locations;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.travelapp.travelapp.model.postedpictures.PicturePlace;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "city")
    private String city;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "country_id")
    @JsonBackReference(value = "country-city")
    private Country country;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city", cascade = CascadeType.ALL)
    private List<Commune> communes;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    @JsonBackReference(value = "picturePlaces-city")
    private List<PicturePlace> picturePlaces;

    public City() {}
    public City(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Commune> getCommunes() {
        return communes;
    }

    public void setCommunes(List<Commune> communes) {
        this.communes = communes;
    }

    public void addCommune(Commune commune){
        if(communes == null){
            communes = new ArrayList<>();
        }

        communes.add(commune);
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
        return "City{" +
                "id=" + id +
                ", city='" + city + '\'' +
                '}';
    }
}
