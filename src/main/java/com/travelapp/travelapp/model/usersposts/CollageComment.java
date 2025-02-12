package com.travelapp.travelapp.model.usersposts;

import com.travelapp.travelapp.model.userrelated.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "collage_comment")
public class CollageComment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "collage_id")
    private Collage collage;

    @Column(name = "comment_date")
    private LocalDateTime dateTime;

    @Column(name = "comment")
    private String comment;

    public CollageComment() {}
    public CollageComment(String comment) {
        this.comment = comment;
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

    public Collage getCollagePost() {
        return collage;
    }

    public void setCollage(Collage collage) {
        this.collage = collage;
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

    @Override
    public String toString() {
        return "CollageComment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
