package com.travelapp.travelapp.model.postedpictures;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.travelapp.travelapp.model.locations.*;
import jakarta.persistence.*;

@Entity
@Table(name = "picture_place")
public class PicturePlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "file_id", nullable = false)
    @JsonBackReference(value = "touristicPicture-picturePlace")
    private TouristicPicture touristicPicture;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "commune_id")
    private Commune commune;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "village_id")
    private Village village;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "place_name_id")
    private PlaceName placeName;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "place_type_id")
    private PlaceType placeType;

    public PicturePlace() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TouristicPicture getTouristicPicture() {
        return touristicPicture;
    }

    public void setTouristicPicture(TouristicPicture touristicPicture) {
        this.touristicPicture = touristicPicture;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public PlaceName getPlaceName() {
        return placeName;
    }

    public void setPlaceName(PlaceName placeName) {
        this.placeName = placeName;
    }

    public PlaceType getPlaceType() {
        return placeType;
    }

    public void setPlaceType(PlaceType placeType) {
        this.placeType = placeType;
    }

    @Override
    public String toString() {
        return "PicturePlace{" +
                "id=" + id +
                ", country=" + country +
                ", city=" + city +
                ", commune=" + commune +
                ", village=" + village +
                ", placeName=" + placeName +
                ", placeType=" + placeType +
                '}';
    }
}
