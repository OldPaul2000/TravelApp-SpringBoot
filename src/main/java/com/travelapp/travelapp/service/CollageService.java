package com.travelapp.travelapp.service;

import com.travelapp.travelapp.dto.collagepost.*;
import com.travelapp.travelapp.dto.mappers.*;
import com.travelapp.travelapp.dto.postedpictures.PostingUserDTOGet;
import com.travelapp.travelapp.dto.postedpictures.TouristicPictureDTOGet;
import com.travelapp.travelapp.model.postedpictures.TouristicPicture;
import com.travelapp.travelapp.model.userrelated.User;
import com.travelapp.travelapp.model.usersposts.Collage;
import com.travelapp.travelapp.model.usersposts.CollageComment;
import com.travelapp.travelapp.model.usersposts.CollageLike;
import com.travelapp.travelapp.repository.CollageRepository;
import com.travelapp.travelapp.repository.PictureRepository;
import com.travelapp.travelapp.repository.UserRepository;
import com.travelapp.travelapp.restcontroller.exceptionhandling.collageposts.CollagePostAlreadyLikedException;
import com.travelapp.travelapp.restcontroller.exceptionhandling.collageposts.CollagePostNotFoundException;
import com.travelapp.travelapp.restcontroller.exceptionhandling.users.UserNotFoundException;
import com.travelapp.travelapp.securityexceptionhandling.UserNotMatchingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage.CollagePostErrorMessages.COLLAGE_POST_ALREADY_LIKED;
import static com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage.CollagePostErrorMessages.COLLAGE_POST_NOT_FOUND;
import static com.travelapp.travelapp.restcontroller.exceptionhandling.customerrormessage.UserErrorMessages.USER_NOT_FOUND;
import static com.travelapp.travelapp.securityexceptionhandling.SecurityErrorMessages.USER_NOT_MATCHING;

@Service
public class CollageService {

    private CurrentUserVerifier currentUserVerifier;

    private CollageRepository collageRepository;
    private UserRepository userRepository;
    private PictureRepository pictureRepository;

    private PostingUserMapper postingUserMapper;
    private TouristicPictureMapper touristicPictureMapper;
    private CollagePostMapper collageMapper;
    private CollageLikeMapper collageLikeMapper;
    private CollageCommentMapper collageCommentMapper;

    @Autowired
    public CollageService(CurrentUserVerifier currentUserVerifier,
                          CollageRepository collageRepository,
                          UserRepository userRepository,
                          PictureRepository pictureRepository,
                          PostingUserMapper postingUserMapper,
                          TouristicPictureMapper touristicPictureMapper,
                          CollagePostMapper collageMapper,
                          CollageLikeMapper collageLikeMapper,
                          CollageCommentMapper collageCommentMapper) {
        this.currentUserVerifier = currentUserVerifier;
        this.collageRepository = collageRepository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.postingUserMapper = postingUserMapper;
        this.touristicPictureMapper = touristicPictureMapper;
        this.collageMapper = collageMapper;
        this.collageLikeMapper = collageLikeMapper;
        this.collageCommentMapper = collageCommentMapper;
    }

    /* Works */
    public CollageDTOGet getCollageById(long id){
        Collage collage = collageRepository.findCollageById(id);
        if(collage == null){
            throw new CollagePostNotFoundException(COLLAGE_POST_NOT_FOUND.message());
        }

        PostingUserDTOGet userDTO = postingUserMapper.toDTO(collage.getUser());
        List<TouristicPictureDTOGet> collagePictures = collage.getTouristicPictures()
                .stream()
                .map(picture -> touristicPictureMapper.toDTO(picture))
                .toList();

        CollageDTOGet collageUserDTOGet = new CollageDTOGet(
                collage.getId(),
                collage.getDescription(),
                collage.getDateTime(),
                userDTO,
                collagePictures
        );

        return collageUserDTOGet;
    }

    /* Works */
    public List<CollageDTOGet> getCollagesFromUser(long userId){
        return collageRepository.findCollagesByUserId(userId)
                .stream()
                .map(collage -> collageMapper.toDTO(collage))
                .toList();
    }

    /* Works */
    // Duplicate pictures error is not handled
    public void postNewCollage(long userId, CollageDTOPost collageDTO){
        User user = userRepository.findUserById(userId);
        if(!currentUserVerifier.isCurrentUser(user.getUsername())){
            throw new UserNotMatchingException(USER_NOT_MATCHING.message());
        }
        List<TouristicPicture> pictures = collageDTO.pictures().stream()
                .map(pictureId -> pictureRepository.findTouristicPictureById(pictureId))
                .toList();

        Collage collage = new Collage();
        collage.setDescription(collageDTO.description());
        collage.setDateTime(LocalDateTime.now());
        collage.setUser(user);

        pictures.forEach(picture -> {
            picture.addCollagePost(collage);
        });

        collageRepository.persistNewCollage(collage);
    }

    /* Works */
    public void deleteCollage(long userId, long collageId){
        try{
            Collage collage = collageRepository.findCollageByCollageAndUserId(collageId, userId);
            if(!currentUserVerifier.isCurrentUser(collage.getUser().getUsername())){
                throw new UserNotMatchingException(USER_NOT_MATCHING.message());
            }
            collage.getTouristicPictures().forEach(picture -> {
                picture.getCollagePosts().remove(collage);
            });
            collage.setTouristicPictures(null);

            collageRepository.removeCollage(collage);
        }
        catch (EmptyResultDataAccessException e){
            throw new CollagePostNotFoundException(COLLAGE_POST_NOT_FOUND.message());
        }
    }

    /* Works */
    // Collage not found error not handled
    public void postCollageComment(long userId, long collageId, CollageCommentDTOPost userComment){
        User user = userRepository.findUserById(userId);
        if(!currentUserVerifier.isCurrentUser(user.getUsername())){
            throw new UserNotMatchingException(USER_NOT_MATCHING.message());
        }
        if(user == null){
            throw new UserNotFoundException(USER_NOT_FOUND.message());
        }

        try{
            Collage collage = collageRepository.findCollageById(collageId);

            CollageComment comment = new CollageComment(userComment.comment());
            comment.setDateTime(LocalDateTime.now());
            comment.setCollage(collage);
            comment.setUser(user);

            collageRepository.persistNewCollageComment(comment);
        }
        catch (EmptyResultDataAccessException e){
            throw new CollagePostNotFoundException(COLLAGE_POST_NOT_FOUND.message());
        }
    }

    /* Works */
    public List<CollageCommentDTOGet> getCollageComments(long collageId){
        List<CollageComment> collageComments = collageRepository.findCollageComments(collageId);
        return collageComments
                .stream()
                .map(comment -> collageCommentMapper.toDTO(comment))
                .toList();
    }

    /* Works */
    public Long getCollageCommentsCount(long collageId){
        return collageRepository.findCollageCommentsCount(collageId);
    }

    /* Works */
    public void deleteCollageComment(long userId, long commentId){
        CollageComment comment = collageRepository.findCollageComment(userId, commentId);
        if(!currentUserVerifier.isCurrentUser(comment.getUser().getUsername())){
            throw new UserNotMatchingException(USER_NOT_MATCHING.message());
        }

        comment.setUser(null);
        comment.setCollage(null);

        collageRepository.removeCollageComment(comment);
    }

    /* Works */
    public void likeCollage(long userId, long collageId){
        User user = userRepository.findUserById(userId);
        if(!currentUserVerifier.isCurrentUser(user.getUsername())){
            throw new UserNotMatchingException(USER_NOT_MATCHING.message());
        }
        if(user == null){
            throw new UserNotFoundException(USER_NOT_FOUND.message());
        }

        Collage collage = collageRepository.findCollageById(collageId);
        if(collage == null){
            throw new CollagePostNotFoundException(COLLAGE_POST_NOT_FOUND.message());
        }

        CollageLike like = new CollageLike();
        like.setCollage(collage);
        like.setUser(user);

        try{
            collageRepository.persistNewCollageLike(like);
        }
        catch (DataIntegrityViolationException e){
            throw new CollagePostAlreadyLikedException(COLLAGE_POST_ALREADY_LIKED.message());
        }
    }

    /* Works */
    public List<CollageLikeDTOGet> getCollageLikes(long collageId){
        return collageRepository.findCollageLikes(collageId)
                .stream()
                .map(like -> collageLikeMapper.toDTO(like))
                .toList();
    }

    /* Works */
    public long getCollageLikesCount(long collageId){
        return collageRepository.findCollageLikesCount(collageId);
    }

    /* Works */
    public void dislikeCollage(long userId, long collageId){
        CollageLike collageLike = collageRepository.findCollageLike(userId, collageId);
        if(!currentUserVerifier.isCurrentUser(collageLike.getUser().getUsername())){
            throw new UserNotMatchingException(USER_NOT_MATCHING.message());
        }
        collageLike.setUser(null);
        collageLike.setCollage(null);
        collageRepository.removeCollageLike(collageLike);
    }

}
