package com.travelapp.travelapp.model.postedpictures;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.travelapp.travelapp.model.userrelated.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "picture_comment")
public class PictureComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "comment_date")
    private LocalDateTime dateTime;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "file_id", nullable = false)
    @JsonBackReference("touristicPicture-pictureComment")
    private TouristicPicture touristicPicture;

    @Column(name = "edited")
    private boolean edited;


    public PictureComment() {}
    public PictureComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TouristicPicture getTouristicPicture() {
        return touristicPicture;
    }

    public void setTouristicPicture(TouristicPicture touristicPicture) {
        this.touristicPicture = touristicPicture;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    @Override
    public String toString() {
        return "PictureComment{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", comment='" + comment + '\'' +
                ", user=" + user +
                ", touristicPicture=" + touristicPicture +
                ", edited=" + edited +
                '}';
    }
}
