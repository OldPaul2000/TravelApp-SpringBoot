package com.travelapp.travelapp.model.postedpictures;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "gps_coords")
public class GpsCoords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "file_id")
    @JsonBackReference(value = "touristicPicture-coordinates")
    private TouristicPicture touristicPicture;

    public GpsCoords() {}
    public GpsCoords(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public TouristicPicture getTouristicPicture() {
        return touristicPicture;
    }

    public void setTouristicPicture(TouristicPicture touristicPicture) {
        this.touristicPicture = touristicPicture;
    }

    @Override
    public String toString() {
        return "GpsCoords{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
