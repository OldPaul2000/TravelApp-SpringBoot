package com.travelapp.travelapp.service;

import com.travelapp.travelapp.dto.collagepost.*;
import com.travelapp.travelapp.dto.mappers.*;
import com.travelapp.travelapp.dto.postedpictures.PostingUserDTOGet;
import com.travelapp.travelapp.dto.postedpictures.TouristicPictureDTOGet;
import com.travelapp.travelapp.model.postedpictures.TouristicPicture;
import com.travelapp.travelapp.model.userrelated.User;
import com.travelapp.travelapp.model.usersposts.CollagePost;
import com.travelapp.travelapp.model.usersposts.PostComment;
import com.travelapp.travelapp.model.usersposts.PostLike;
import com.travelapp.travelapp.repository.CollagePostRepository;
import com.travelapp.travelapp.repository.PictureRepository;
import com.travelapp.travelapp.repository.UserRepository;
import com.travelapp.travelapp.restcontroller.exceptionhandling.collageposts.CollagePostAlreadyLikedException;
import com.travelapp.travelapp.restcontroller.exceptionhandling.collageposts.CollagePostNotFoundException;
import com.travelapp.travelapp.restcontroller.exceptionhandling.users.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage.CollagePostErrorMessages.COLLAGE_POST_ALREADY_LIKED;
import static com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage.CollagePostErrorMessages.COLLAGE_POST_NOT_FOUND;
import static com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage.UserErrorMessages.USER_NOT_FOUND;

@Service
public class CollagePostService {

    private CollagePostRepository collagePostRepository;
    private UserRepository userRepository;
    private PictureRepository pictureRepository;

    private PostingUserMapper postingUserMapper;
    private TouristicPictureMapper touristicPictureMapper;
    private CollagePostMapper collageMapper;
    private CollageLikeMapper collageLikeMapper;
    private CollageCommentMapper collageCommentMapper;

    @Autowired
    public CollagePostService(CollagePostRepository collagePostRepository,
                              UserRepository userRepository,
                              PictureRepository pictureRepository,
                              PostingUserMapper postingUserMapper,
                              TouristicPictureMapper touristicPictureMapper,
                              CollagePostMapper collageMapper,
                              CollageLikeMapper collageLikeMapper,
                              CollageCommentMapper collageCommentMapper) {
        this.collagePostRepository = collagePostRepository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.postingUserMapper = postingUserMapper;
        this.touristicPictureMapper = touristicPictureMapper;
        this.collageMapper = collageMapper;
        this.collageLikeMapper = collageLikeMapper;
        this.collageCommentMapper = collageCommentMapper;
    }

    /* Works */
    public CollagePostDTOGet getCollageById(int id){
        CollagePost collagePost = collagePostRepository.getCollagePostById(id);

        if(collagePost == null){
            throw new CollagePostNotFoundException(COLLAGE_POST_NOT_FOUND.message());
        }

        PostingUserDTOGet userDTO = postingUserMapper.toDTO(collagePost.getUser());
        List<TouristicPictureDTOGet> collagePictures = collagePost.getTouristicPictures()
                .stream()
                .map(picture -> touristicPictureMapper.toDTO(picture))
                .toList();

        CollagePostDTOGet collageUserDTOGet = new CollagePostDTOGet(
                collagePost.getId(),
                collagePost.getDescription(),
                collagePost.getDateTime(),
                userDTO,
                collagePictures
        );

        return collageUserDTOGet;
    }

    /* Works */
    public List<CollagePostDTOGet> getCollagesFromUser(long userId){
        return collagePostRepository.getCollagePostsByUserId(userId)
                .stream()
                .map(collage -> collageMapper.toDTO(collage))
                .toList();
    }

    /* Works */
    // Duplicate pictures error is not handled
    public void postNewCollage(CollagePostDTOPost collagePost){
        User user = userRepository.findUserById(collagePost.userId());
        List<TouristicPicture> pictures = collagePost.pictures().stream()
                .map(pictureId -> pictureRepository.getTouristicPictureById(pictureId))
                .toList();

        CollagePost collage = new CollagePost();
        collage.setDescription(collagePost.description());
        collage.setDateTime(LocalDateTime.now());
        collage.setUser(user);

        pictures.forEach(picture -> {
            picture.addCollagePost(collage);
        });

        collagePostRepository.addNewCollage(collage);
    }

    /* Works */
    public void deleteCollage(long userId, long collageId){
        try{
            CollagePost collagePost = collagePostRepository.getCollageByUserId(collageId, userId);
            collagePost.getTouristicPictures().forEach(picture -> {
                picture.getCollagePosts().remove(collagePost);
            });
            collagePost.setTouristicPictures(null);

            collagePostRepository.deleteCollage(collagePost);
        }
        catch (EmptyResultDataAccessException e){
            throw new CollagePostNotFoundException(COLLAGE_POST_NOT_FOUND.message());
        }
    }

    /* Works */
    // Collage not found error not handled
    public void postCollageComment(long userId, long collageId, CollageCommentDTOPost userComment){
        User user = userRepository.findUserById(userId);
        if(user == null){
            throw new UserNotFoundException(USER_NOT_FOUND.message());
        }

        try{
            CollagePost collagePost = collagePostRepository.getCollagePostById(collageId);

            PostComment comment = new PostComment(userComment.userComment());
            comment.setDateTime(LocalDateTime.now());
            comment.setCollagePost(collagePost);
            comment.setUser(user);

            collagePostRepository.addCollageComment(comment);
        }
        catch (EmptyResultDataAccessException e){
            throw new CollagePostNotFoundException(COLLAGE_POST_NOT_FOUND.message());
        }
    }

    /* Works */
    public void deleteCollageComment(long userId, long commentId){
        PostComment comment = collagePostRepository.getCollageComment(userId, commentId);

        comment.setUser(null);
        comment.setCollagePost(null);

        collagePostRepository.deletePostComment(comment);
    }

    /* Works */
    public List<CollageCommentDTOGet> getCollageComments(long collageId){
        List<PostComment> postComments = collagePostRepository.getCollageComments(collageId);
        return postComments
                .stream()
                .map(comment -> collageCommentMapper.toDTO(comment))
                .toList();
    }

    /* Works */
    public Long getCollageCommentsCount(long collageId){
        return collagePostRepository.getCollageCommentsCount(collageId);
    }

    /* Works */
    public void likeCollage(long userId, long collageId){
        User user = userRepository.findUserById(userId);
        if(user == null){
            throw new UserNotFoundException(USER_NOT_FOUND.message());
        }

        CollagePost collage = collagePostRepository.getCollagePostById(collageId);
        if(collage == null){
            throw new CollagePostNotFoundException(COLLAGE_POST_NOT_FOUND.message());
        }

        PostLike like = new PostLike();
        like.setCollagePost(collage);
        like.setUser(user);

        try{
            collagePostRepository.addCollageLike(like);
        }
        catch (DataIntegrityViolationException e){
            throw new CollagePostAlreadyLikedException(COLLAGE_POST_ALREADY_LIKED.message());
        }
    }

    /* Works */
    public void dislikeCollage(long userId, long collageId){
        PostLike postLike = collagePostRepository.getPostLike(userId, collageId);
        postLike.setUser(null);
        postLike.setCollagePost(null);
        collagePostRepository.removeCollageLike(postLike);
    }

    /* Works */
    public List<CollageLikeDTOGet> getCollageLikes(long collageId){
        return collagePostRepository.getPostLikes(collageId)
                .stream()
                .map(like -> collageLikeMapper.toDTO(like))
                .toList();
    }

    /* Works */
    public long getCollagePostLikesCount(long collageId){
        return collagePostRepository.getCollageLikesCount(collageId);
    }

}
