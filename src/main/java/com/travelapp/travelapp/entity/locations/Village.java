package com.travelapp.travelapp.entity.locations;

import jakarta.persistence.*;

@Entity
@Table(name = "village")
public class Village {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "commune_id")
    private int communeId;

    @Column(name = "village")
    private String village;

    public Village() {}
    public Village(int id, int communeId, String village) {
        this.id = id;
        this.communeId = communeId;
        this.village = village;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommuneId() {
        return communeId;
    }

    public void setCommuneId(int communeId) {
        this.communeId = communeId;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    @Override
    public String toString() {
        return "Village{" +
                "id=" + id +
                ", communeId=" + communeId +
                ", village='" + village + '\'' +
                '}';
    }
}
