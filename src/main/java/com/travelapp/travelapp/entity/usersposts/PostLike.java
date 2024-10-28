package com.travelapp.travelapp.entity.usersposts;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "post_like")
public class PostLike {

    private int postId;
    private int userId;

    public PostLike() {}
    public PostLike(int postId, int userId) {
        this.postId = postId;
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "PostLike{" +
                "postId=" + postId +
                ", userId=" + userId +
                '}';
    }
}
