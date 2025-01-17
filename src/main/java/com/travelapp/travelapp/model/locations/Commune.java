package com.travelapp.travelapp.model.locations;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.travelapp.travelapp.model.postedpictures.PicturePlace;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "commune")
public class Commune {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "commune")
    private String commune;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "city_id")
    @JsonBackReference(value = "city-commune")
    private City city;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commune", cascade = CascadeType.ALL)
    private List<Village> villages;

    @OneToMany(mappedBy = "commune",cascade = CascadeType.ALL)
    @JsonBackReference(value = "picturePlaces-commune")
    private List<PicturePlace> picturePlaces;

    public Commune() {}
    public Commune(String commune) {
        this.commune = commune;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Village> getVillages() {
        return villages;
    }

    public void setVillages(List<Village> villages) {
        this.villages = villages;
    }

    public void addVillage(Village village){
        if(villages == null){
            villages = new ArrayList<>();
        }

        villages.add(village);
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
        return "Commune{" +
                "id=" + id +
                ", commune='" + commune + '\'' +
                '}';
    }
}
