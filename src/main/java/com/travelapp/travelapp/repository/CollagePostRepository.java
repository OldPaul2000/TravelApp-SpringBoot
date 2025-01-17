package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.usersposts.CollagePost;
import com.travelapp.travelapp.model.usersposts.PostComment;
import com.travelapp.travelapp.model.usersposts.PostLike;

import java.util.List;

public interface CollagePostRepository {

    CollagePost getCollagePostById(long id);

    CollagePost getCollageByUserId(long collageId, long userId);

    List<CollagePost> getCollagePostsByUserId(long userId);

    void addNewCollage(CollagePost collagePost);

    void updateCollage(CollagePost collagePost);

    void deleteCollage(CollagePost collagePost);

    void addCollageComment(PostComment postComment);

    PostComment getCollageComment(long userId, long commentId);

    List<PostComment> getCollageComments(long collageId);

    long getCollageCommentsCount(long collageId);

    void deletePostComment(PostComment postComment);

    void addCollageLike(PostLike postLike);

    void removeCollageLike(PostLike postLike);

    PostLike getPostLike(long userId, long collageId);

    List<PostLike> getPostLikes(long collageId);

    long getCollageLikesCount(long collageId);

}
