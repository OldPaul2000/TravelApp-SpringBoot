package com.travelapp.travelapp.repository;

import com.travelapp.travelapp.model.usersposts.Collage;
import com.travelapp.travelapp.model.usersposts.CollageComment;
import com.travelapp.travelapp.model.usersposts.CollageLike;

import java.util.List;

public interface CollageRepository {

    Collage findCollageById(long id);

    List<Collage> findCollagesByUserId(long userId, int startPage, int offset);

    void persistNewCollage(Collage collage);

    void removeCollage(Collage collage);

    void persistNewCollageComment(CollageComment collageComment);
    Collage findCollageByCollageAndUserId(long collageId, long userId);

    void mergeCollageComment(CollageComment collageComment);

    void removeCollageComment(CollageComment collageComment);
    CollageComment findCollageComment(long userId, long commentId);

    List<CollageComment> findCollageComments(long collageId, int pageStart, int offset);

    long findCollageCommentsCount(long collageId);

    void mergeCollage(Collage collage);

    void persistNewCollageLike(CollageLike collageLike);

    void removeCollageLike(CollageLike collageLike);
    CollageLike findCollageLike(long userId, long collageId);

    List<CollageLike> findCollageLikes(long collageId, int pageStart, int offset);

    long findCollageLikesCount(long collageId);

}
