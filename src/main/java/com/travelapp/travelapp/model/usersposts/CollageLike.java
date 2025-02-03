package com.travelapp.travelapp.model.usersposts;

import com.travelapp.travelapp.model.userrelated.User;
import jakarta.persistence.*;

@Entity
@Table(name = "collage_like")
public class CollageLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "collage_id", nullable = false)
    private Collage collage;


    public CollageLike() {}

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

    public void setCollagePost(Collage collage) {
        this.collage = collage;
    }

    @Override
    public String toString() {
        return "CollageLike{" +
                "id=" + id +
                ", userId=" + user.getId() +
                ", collagePostId=" + collage.getId() +
                '}';
    }
}
