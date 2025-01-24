package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.usersposts.CollageComment;
import com.travelapp.travelapp.model.usersposts.CollageLike;
import com.travelapp.travelapp.model.usersposts.CollagePost;

import java.util.List;

public interface CollageRepository {

    CollagePost findCollageById(long id);

    List<CollagePost> findCollagesByUserId(long userId);

    void persistNewCollage(CollagePost collagePost);

    void removeCollage(CollagePost collagePost);

    void persistNewCollageComment(CollageComment collageComment);
    CollagePost findCollageByCollageAndUserId(long collageId, long userId);

    void removeCollageComment(CollageComment collageComment);
    CollageComment findCollageComment(long userId, long commentId);

    List<CollageComment> findCollageComments(long collageId);

    long findCollageCommentsCount(long collageId);

    void mergeCollage(CollagePost collagePost);

    void persistNewCollageLike(CollageLike collageLike);

    void removeCollageLike(CollageLike collageLike);
    CollageLike findCollageLike(long userId, long collageId);

    List<CollageLike> findCollageLikes(long collageId);

    long findCollageLikesCount(long collageId);

}
