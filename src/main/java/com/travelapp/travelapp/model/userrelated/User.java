package com.travelapp.travelapp.model.userrelated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.travelapp.travelapp.model.postedpictures.PictureComment;
import com.travelapp.travelapp.model.postedpictures.PictureLike;
import com.travelapp.travelapp.model.postedpictures.TouristicPicture;
import com.travelapp.travelapp.model.usersposts.CollagePost;
import com.travelapp.travelapp.model.usersposts.PostComment;
import com.travelapp.travelapp.model.usersposts.PostLike;
import com.travelapp.travelapp.repository.LazyFieldsFilter;
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = LazyFieldsFilter.class)
    private List<Role> roles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserInfo userInfo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = LazyFieldsFilter.class)
    private List<PictureLike> pictureLikes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = LazyFieldsFilter.class)
    private List<PictureComment> pictureComments;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = LazyFieldsFilter.class)
    private List<TouristicPicture> touristicPictures;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = LazyFieldsFilter.class)
    private List<CollagePost> collagePosts;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = LazyFieldsFilter.class)
    private List<PostLike> postLikes;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonInclude(value = JsonInclude.Include.CUSTOM, valueFilter = LazyFieldsFilter.class)
    private List<PostComment> postComments;

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

    public void addNewCollagePost(CollagePost collagePost){
        if(collagePosts == null){
            collagePosts = new ArrayList<>();
        }

        collagePosts.add(collagePost);
    }

    public List<PostLike> getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(List<PostLike> postLikes) {
        this.postLikes = postLikes;
    }

    public void addNewPostLike(PostLike postLike){
        if(postLikes == null){
            postLikes.add(postLike);
        }

        postLikes.add(postLike);
    }

    public List<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<PostComment> postComments) {
        this.postComments = postComments;
    }

    public void addNewPostComment(PostComment postComment){
        if(postComments == null){
            postComments = new ArrayList<>();
        }

        postComments.add(postComment);
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
