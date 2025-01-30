package com.travelapp.travelapp.model.postedpictures;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.travelapp.travelapp.model.userrelated.User;
import com.travelapp.travelapp.model.usersposts.CollagePost;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "touristic_picture")
public class TouristicPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-touristicPictures")
    private User user;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "capture_date")
    private LocalDateTime captureDateTime;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "touristicPicture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PictureLike> pictureLikes;

    @OneToMany(mappedBy = "touristicPicture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PictureComment> pictureComments;

    @OneToOne(mappedBy = "touristicPicture", cascade = CascadeType.ALL, orphanRemoval = true)
    private GpsCoords coordinates;

    @OneToOne(mappedBy = "touristicPicture", cascade = CascadeType.ALL, orphanRemoval = true)
    private PicturePlace picturePlace;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "collage_picture",
               joinColumns = @JoinColumn(name = "picture_id"),
               inverseJoinColumns = @JoinColumn(name = "collage_id"))
    private List<CollagePost> collagePosts;



    public TouristicPicture() {}
    public TouristicPicture(String fileName, String description) {
        this.fileName = fileName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public LocalDateTime getCaptureDateTime() {
        return captureDateTime;
    }

    public void setCaptureDateTime(LocalDateTime captureDateTime) {
        this.captureDateTime = captureDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PictureLike> getPictureLikes() {
        return pictureLikes;
    }

    public void setPictureLikes(List<PictureLike> likes) {
        this.pictureLikes = likes;
    }

    public void addPictureLike(PictureLike like){
        if(pictureLikes == null){
            pictureLikes = new ArrayList<>();
        }

        pictureLikes.add(like);
    }

    public List<PictureComment> getPictureComments() {
        return pictureComments;
    }

    public void setPictureComments(List<PictureComment> pictureComments) {
        this.pictureComments = pictureComments;
    }

    public void addPictureComment(PictureComment pictureComment){
        if(pictureComments == null){
            pictureComments = new ArrayList<>();
        }

        pictureComments.add(pictureComment);
    }

    public GpsCoords getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(GpsCoords coordinates) {
        this.coordinates = coordinates;
    }

    public PicturePlace getPicturePlace() {
        return picturePlace;
    }

    public void setPicturePlace(PicturePlace picturePlace) {
        this.picturePlace = picturePlace;
    }


    public List<CollagePost> getCollagePosts() {
        return collagePosts;
    }

    public void setCollagePosts(List<CollagePost> collagePosts) {
        this.collagePosts = collagePosts;
    }

    public void addCollagePost(CollagePost collagePost){
        if(collagePosts == null){
            collagePosts = new ArrayList<>();
        }

        collagePosts.add(collagePost);
    }

    @Override
    public String toString() {
        return "TouristicPicture{\n" +
                "id=" + id + "\n" +
//                ", user=" + user +
                ", fileName='" + fileName + '\'' + "\n" +
                ", captureDateTime=" + captureDateTime + "\n" +
                ", description='" + description + '\'' + "\n" +
                ", picturePlace=" + picturePlace + "\n" +
//                ", likes=" + likes + "\n" +
                ", coordinates=" + coordinates + "\n" +
//                ", pictureComments=" + pictureComments + "\n" +
                '}';
    }
}
