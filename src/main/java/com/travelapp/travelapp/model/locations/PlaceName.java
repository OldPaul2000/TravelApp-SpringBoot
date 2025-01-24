package com.travelapp.travelapp.model.locations;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.travelapp.travelapp.model.postedpictures.PicturePlace;
import jakarta.persistence.*;

@Entity
@Table(name = "place_name")
public class PlaceName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "placeName", cascade = CascadeType.ALL)
    @JsonBackReference(value = "picturePlace-placeName")
    private PicturePlace picturePlace;

    public PlaceName() {}
    public PlaceName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PicturePlace getPicturePlace() {
        return picturePlace;
    }

    public void setPicturePlace(PicturePlace picturePlace) {
        this.picturePlace = picturePlace;
    }

    @Override
    public String toString() {
        return "PlaceName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
