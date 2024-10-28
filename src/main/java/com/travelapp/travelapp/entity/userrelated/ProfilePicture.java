package com.travelapp.travelapp.entity.userrelated;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "profile_picture")
public class ProfilePicture {

    @Column(name = "user_info_id")
    private int userInfoId;

    @Column(name = "file_name")
    private String fileName;

    public ProfilePicture() {}
    public ProfilePicture(int userInfoId, String fileName) {
        this.userInfoId = userInfoId;
        this.fileName = fileName;
    }

    public int getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
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
                "userInfoId=" + userInfoId +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
