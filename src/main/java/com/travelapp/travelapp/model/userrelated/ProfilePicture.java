package com.travelapp.travelapp.model.userrelated;

import jakarta.persistence.*;

@Entity
@Table(name = "profile_picture")
public class ProfilePicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    public ProfilePicture() {}
    public ProfilePicture(String fileName) {
        this.fileName = fileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "ProfilePicture{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
