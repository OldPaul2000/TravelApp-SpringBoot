package com.travelapp.travelapp.model.locations;

import com.travelapp.travelapp.model.postedpictures.PicturePlace;
import jakarta.persistence.*;

@Entity
@Table(name = "place_type")
public class PlaceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "place_type")
    private String placeType;

    @OneToMany(mappedBy = "placeType",cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private PicturePlace picturePlace;

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

    public PicturePlace getPicturePlace() {
        return picturePlace;
    }

    public void setPicturePlace(PicturePlace picturePlace) {
        this.picturePlace = picturePlace;
    }

    @Override
    public String toString() {
        return "PlaceType{" +
                "id=" + id +
                ", placeType='" + placeType + '\'' +
                '}';
    }
}
