package com.travelapp.travelapp.entity.usersposts;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "collage_post")
public class CollagePost {

    private int id;
    private int userId;
    private LocalDateTime dateTime;
    private String description;

    public CollagePost() {}
    public CollagePost(int id, int userId, LocalDateTime dateTime, String description) {
        this.id = id;
        this.userId = userId;
        this.dateTime = dateTime;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CollagePost{" +
                "id=" + id +
                ", userId=" + userId +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                '}';
    }
}
