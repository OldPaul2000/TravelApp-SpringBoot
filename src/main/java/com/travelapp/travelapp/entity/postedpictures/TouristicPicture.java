package com.travelapp.travelapp.entity.postedpictures;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "touristic_picture")
public class TouristicPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "capture_date")
    private LocalDateTime captureDate;

    public TouristicPicture() {}
    public TouristicPicture(int id, int userId, String fileName, LocalDateTime captureDate) {
        this.id = id;
        this.userId = userId;
        this.fileName = fileName;
        this.captureDate = captureDate;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDateTime getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(LocalDateTime captureDate) {
        this.captureDate = captureDate;
    }

    @Override
    public String toString() {
        return "TouristicPicture{" +
                "id=" + id +
                ", userId=" + userId +
                ", fileName='" + fileName + '\'' +
                ", captureDate=" + captureDate +
                '}';
    }
}
