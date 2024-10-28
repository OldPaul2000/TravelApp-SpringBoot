package com.travelapp.travelapp.entity.usersposts;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "collage_pictures")
public class CollagePictures {

    @Column(name = "collage_id")
    private int collageId;

    @Column(name = "picture_id")
    private int pictureId;

    public CollagePictures() {}
    public CollagePictures(int collageId, int pictureId) {
        this.collageId = collageId;
        this.pictureId = pictureId;
    }

    public int getCollageId() {
        return collageId;
    }

    public void setCollageId(int collageId) {
        this.collageId = collageId;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    @Override
    public String toString() {
        return "CollagePictures{" +
                "collageId=" + collageId +
                ", pictureId=" + pictureId +
                '}';
    }
}
