package com.travelapp.travelapp.entity.locations;

import jakarta.persistence.*;

@Entity
@Table(name = "other")
public class Other {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "village_id")
    private int villageId;

    @Column(name = "other")
    private String other;

    public Other() {}
    public Other(int id, int villageId, String other) {
        this.id = id;
        this.villageId = villageId;
        this.other = other;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVillageId() {
        return villageId;
    }

    public void setVillageId(int villageId) {
        this.villageId = villageId;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "Other{" +
                "id=" + id +
                ", villageId=" + villageId +
                ", other='" + other + '\'' +
                '}';
    }
}
