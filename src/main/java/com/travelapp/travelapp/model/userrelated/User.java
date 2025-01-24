package com.travelapp.travelapp.model.userrelated;

import com.travelapp.travelapp.model.postedpictures.PictureComment;
import com.travelapp.travelapp.model.postedpictures.PictureLike;
import com.travelapp.travelapp.model.postedpictures.TouristicPicture;
import com.travelapp.travelapp.model.usersposts.CollageComment;
import com.travelapp.travelapp.model.usersposts.CollageLike;
import com.travelapp.travelapp.model.usersposts.CollagePost;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private byte enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Role> roles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private UserInfo userInfo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PictureLike> pictureLikes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PictureComment> pictureComments;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<TouristicPicture> touristicPictures;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CollagePost> collagePosts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CollageLike> collageLikes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CollageComment> collageComments;

    public User() {}
    public User(Long id, String username, String password, byte enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getEnabled() {
        return enabled;
    }

    public void setEnabled(byte enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role){
        if(roles == null){
            roles = new ArrayList<>();
        }

        roles.add(role);
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
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

    public void addPictureComment(PictureComment comment){
        if(pictureComments == null){
            pictureComments = new ArrayList<>();
        }

        pictureComments.add(comment);
    }

    public List<TouristicPicture> getTouristicPictures() {
        return touristicPictures;
    }

    public void setTouristicPictures(List<TouristicPicture> touristicPictures) {
        this.touristicPictures = touristicPictures;
    }

    public void addTouristicPicture(TouristicPicture picture){
        if(touristicPictures == null){
            touristicPictures = new ArrayList<>();
        }

        touristicPictures.add(picture);
    }

    public List<CollagePost> getCollagePosts() {
        return collagePosts;
    }

    public void setCollagePosts(List<CollagePost> collagePosts) {
        this.collagePosts = collagePosts;
    }

    public void addNewCollage(CollagePost collagePost){
        if(collagePosts == null){
            collagePosts = new ArrayList<>();
        }

        collagePosts.add(collagePost);
    }

    public List<CollageLike> getCollageLikes() {
        return collageLikes;
    }

    public void setCollageLikes(List<CollageLike> collageLikes) {
        this.collageLikes = collageLikes;
    }

    public void addNewCollageLike(CollageLike collageLike){
        if(collageLikes == null){
            collageLikes.add(collageLike);
        }

        collageLikes.add(collageLike);
    }

    public List<CollageComment> getCollageComments() {
        return collageComments;
    }

    public void setCollageComments(List<CollageComment> collageComments) {
        this.collageComments = collageComments;
    }

    public void addNewCollageComment(CollageComment collageComment){
        if(collageComments == null){
            collageComments = new ArrayList<>();
        }

        collageComments.add(collageComment);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
