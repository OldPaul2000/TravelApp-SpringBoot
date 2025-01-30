package com.travelapp.travelapp.model.locations;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.travelapp.travelapp.model.postedpictures.PicturePlace;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "village")
public class Village {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "village")
    private String village;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "commune_id")
    @JsonBackReference(value = "commune-village")
    private Commune commune;

    @OneToMany(mappedBy = "village",cascade = CascadeType.ALL)
    @JsonBackReference(value = "picturePlaces-village")
    private List<PicturePlace> picturePlaces;

    public Village() {}
    public Village(String village) {
        this.village = village;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
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
        return "Village{" +
                "id=" + id +
                ", village='" + village + '\'' +
                '}';
    }
}
