package com.travelapp.travelapp.entity.postedpictures;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "picture_like")
public class PictureLike {

    private int fileId;
    private int userId;

    public PictureLike() {}
    public PictureLike(int fileId, int userId) {
        this.fileId = fileId;
        this.userId = userId;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "PictureLike{" +
                "fileId=" + fileId +
                ", userId=" + userId +
                '}';
    }
}
