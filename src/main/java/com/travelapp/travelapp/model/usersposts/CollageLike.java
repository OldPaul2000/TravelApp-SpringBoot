package com.travelapp.travelapp.model.usersposts;

import com.travelapp.travelapp.model.userrelated.User;
import jakarta.persistence.*;

@Entity
@Table(name = "post_like")
public class CollageLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "post_id", nullable = false)
    private CollagePost collagePost;


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

    public CollagePost getCollagePost() {
        return collagePost;
    }

    public void setCollagePost(CollagePost collagePost) {
        this.collagePost = collagePost;
    }

    @Override
    public String toString() {
        return "CollageLike{" +
                "id=" + id +
                ", userId=" + user.getId() +
                ", collagePostId=" + collagePost.getId() +
                '}';
    }
}
