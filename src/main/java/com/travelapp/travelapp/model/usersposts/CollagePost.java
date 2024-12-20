package com.travelapp.travelapp.model.usersposts;

import com.travelapp.travelapp.model.postedpictures.TouristicPicture;
import com.travelapp.travelapp.model.userrelated.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "collage_post")
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

    @OneToMany(mappedBy = "collagePost", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<PostLike> postLikes;

    @OneToMany(mappedBy = "collagePost", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<PostComment> postComments;

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

    public List<PostLike> getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(List<PostLike> postLikes) {
        this.postLikes = postLikes;
    }

    public List<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<PostComment> postComments) {
        this.postComments = postComments;
    }

    public List<TouristicPicture> getTouristicPictures() {
        return touristicPictures;
    }

    public void setTouristicPictures(List<TouristicPicture> touristicPictures) {
        this.touristicPictures = touristicPictures;
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
