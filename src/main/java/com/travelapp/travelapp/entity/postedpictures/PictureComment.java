package com.travelapp.travelapp.entity.postedpictures;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "picture_comment")
public class PictureComment {

    @Column(name = "file_id")
    private int fileId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "comment")
    private String comment;

    public PictureComment() {}
    public PictureComment(int fileId, int userId, String comment) {
        this.fileId = fileId;
        this.userId = userId;
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "PictureComment{" +
                "fileId=" + fileId +
                ", userId=" + userId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
