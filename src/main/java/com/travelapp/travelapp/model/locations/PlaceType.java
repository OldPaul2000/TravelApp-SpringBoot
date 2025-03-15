package com.travelapp.travelapp.model.locations;

import com.travelapp.travelapp.model.postedpictures.PicturePlace;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "place_type")
public class PlaceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "place_type")
    private String placeType;

    @OneToMany(mappedBy = "placeType", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<PicturePlace> picturePlaces;

    public PlaceType() {}

    public PlaceType(String placeType) {
        this.placeType = placeType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public List<PicturePlace> getPicturePlace() {
        return picturePlaces;
    }

    public void setPicturePlaces(List<PicturePlace> picturePlace) {
        this.picturePlaces = picturePlaces;
    }

    public void addPicturePlace(PicturePlace picturePlace){
        if(this.picturePlaces == null){
            this.picturePlaces = new ArrayList();
        }
        picturePlaces.add(picturePlace);
    }

    public void removePicturePlace(PicturePlace picturePlace){
        if(this.picturePlaces == null){
            this.picturePlaces = new ArrayList();
        }
        picturePlaces.remove(picturePlace);
    }

    @Override
    public String toString() {
        return "PlaceType{" +
                "id=" + id +
                ", placeType='" + placeType + '\'' +
                '}';
    }
}
