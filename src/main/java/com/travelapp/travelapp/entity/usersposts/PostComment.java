package com.travelapp.travelapp.entity.usersposts;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "post_comment")
public class PostComment {


    @Column(name = "post_id")
    private int postId;

    @Column(name = "user_id")
    private int userId;

    public PostComment() {}
    public PostComment(int postId, int userId) {
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
        return "PostComment{" +
                "postId=" + postId +
                ", userId=" + userId +
                '}';
    }
}
