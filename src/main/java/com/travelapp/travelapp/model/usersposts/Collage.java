package com.travelapp.travelapp.model.usersposts;

import com.travelapp.travelapp.model.postedpictures.TouristicPicture;
import com.travelapp.travelapp.model.userrelated.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "collage")
public class CollagePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "post_date")
    private LocalDateTime dateTime;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "collagePost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CollageLike> collageLikes;

    @OneToMany(mappedBy = "collagePost", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CollageComment> collageComments;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "collage_picture",
            joinColumns = @JoinColumn(name = "collage_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id"))
    private List<TouristicPicture> touristicPictures;


    public CollagePost() {}

    public CollagePost(String description) {
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

    public List<CollageLike> getPostLikes() {
        return collageLikes;
    }

    public void setPostLikes(List<CollageLike> collageLikes) {
        this.collageLikes = collageLikes;
    }

    public void addPostLike(CollageLike collageLike){
        if(collageLikes == null){
            collageLikes = new ArrayList<>();
        }

        collageLikes.add(collageLike);
    }

    public List<CollageComment> getPostComments() {
        return collageComments;
    }

    public void setPostComments(List<CollageComment> collageComments) {
        this.collageComments = collageComments;
    }

    public void addPostComment(CollageComment collageComment){
        if(collageComments == null){
            collageComments = new ArrayList<>();
        }

        collageComments.add(collageComment);
    }

    public List<TouristicPicture> getTouristicPictures() {
        return touristicPictures;
    }

    public void setTouristicPictures(List<TouristicPicture> touristicPictures) {
        this.touristicPictures = touristicPictures;
    }

    public void addTouristicPicture(TouristicPicture touristicPicture){
        if(touristicPictures == null){
            touristicPictures = new ArrayList<>();
        }

        touristicPictures.add(touristicPicture);
    }

    @Override
    public String toString() {
        return "CollagePost{" +
                "id=" + id +
                ", user=" + user +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                '}';
    }
}
