package com.travelapp.travelapp.model.postedpictures;

import com.travelapp.travelapp.model.userrelated.User;
import jakarta.persistence.*;

@Entity
@Table(name = "picture_like")
public class PictureLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "file_id", nullable = false)
    private TouristicPicture touristicPicture;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public PictureLike() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TouristicPicture getTouristicPicture() {
        return touristicPicture;
    }

    public void setTouristicPicture(TouristicPicture touristicPicture) {
        this.touristicPicture = touristicPicture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PictureLike{" +
                "id=" + id +
                ", touristicPicture=" + touristicPicture +
                ", user=" + user +
                '}';
    }
}
