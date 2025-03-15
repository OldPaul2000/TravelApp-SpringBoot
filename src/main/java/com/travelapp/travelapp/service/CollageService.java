package com.travelapp.travelapp.service;

import com.travelapp.travelapp.dto.collagepost.*;
import com.travelapp.travelapp.dto.mappers.*;
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

    private UserPrivilegesVerifier userPrivilegesVerifier;

    private CollageRepository collageRepository;
    private UserRepository userRepository;
    private PictureRepository pictureRepository;

    private PostingUserMapper postingUserMapper;
    private TouristicPictureMapper touristicPictureMapper;
    private CollageMapper collageMapper;
    private CollageLikeMapper collageLikeMapper;
    private CollageCommentMapper collageCommentMapper;
    private FileStorageService fileStorageService;

    @Autowired
    public CollageService(UserPrivilegesVerifier userPrivilegesVerifier,
                          CollageRepository collageRepository,
                          UserRepository userRepository,
                          PictureRepository pictureRepository,
                          PostingUserMapper postingUserMapper,
                          TouristicPictureMapper touristicPictureMapper,
                          CollageMapper collageMapper,
                          CollageLikeMapper collageLikeMapper,
                          CollageCommentMapper collageCommentMapper,
                          FileStorageService fileStorageService) {
        this.userPrivilegesVerifier = userPrivilegesVerifier;
        this.collageRepository = collageRepository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.postingUserMapper = postingUserMapper;
        this.touristicPictureMapper = touristicPictureMapper;
        this.collageMapper = collageMapper;
        this.collageLikeMapper = collageLikeMapper;
        this.collageCommentMapper = collageCommentMapper;
        this.fileStorageService = fileStorageService;
    }

    public CollageDTOGet getCollageById(long id){
        Collage collage = collageRepository.findCollageById(id);
        if(collage == null){
            throw new CollagePostNotFoundException(COLLAGE_POST_NOT_FOUND.message());
        }

        return collageMapper.toDTO(collage);
    }

    public List<CollageDTOGet> getCollagesFromUser(long userId, int startPage, int offset){
        return collageRepository.findCollagesByUserId(userId, startPage, offset)
                .stream()
                .map(collage -> collageMapper.toDTO(collage))
                .toList();
    }

    public void postNewCollage(long userId, CollageDTOPost collageDTO){
        User user = userRepository.findUserById(userId);
        if(!userPrivilegesVerifier.isCurrentUser(user.getUsername())){
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

    public void deleteCollage(long userId, long collageId){
        try{
            Collage collage = collageRepository.findCollageByCollageAndUserId(collageId, userId);
            if(!userPrivilegesVerifier.isCurrentUser(collage.getUser().getUsername())){
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

    // Collage not found error not handled
    public void postCollageComment(long userId, long collageId, CollageCommentDTOPost userComment){
        User user = userRepository.findUserById(userId);
        if(!userPrivilegesVerifier.isCurrentUser(user.getUsername())){
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
            comment.setEdited(false);

            collageRepository.persistNewCollageComment(comment);
        }
        catch (EmptyResultDataAccessException e){
            throw new CollagePostNotFoundException(COLLAGE_POST_NOT_FOUND.message());
        }
    }

    public void editCollageComment(long userId, long commentId, CollageCommentDTOPost editedComment){
        CollageComment comment = collageRepository.findCollageComment(userId, commentId);

        if(!userPrivilegesVerifier.isCurrentUser(comment.getUser().getUsername())){
            throw new UserNotMatchingException(USER_NOT_MATCHING.message());
        }
        comment.setComment(editedComment.comment());
        comment.setEdited(true);

        collageRepository.mergeCollageComment(comment);
    }

    public List<CollageCommentDTOGet> getCollageComments(long collageId, int pageStart, int offset){
        List<CollageComment> collageComments = collageRepository.findCollageComments(collageId, pageStart, offset);
        return collageComments
                .stream()
                .map(comment -> collageCommentMapper.toDTO(comment))
                .toList();
    }

    public Long getCollageCommentsCount(long collageId){
        return collageRepository.findCollageCommentsCount(collageId);
    }

    public void deleteCollageComment(long userId, long commentId){
        CollageComment comment = collageRepository.findCollageComment(userId, commentId);
        if(!userPrivilegesVerifier.isCurrentUser(comment.getUser().getUsername())){
            throw new UserNotMatchingException(USER_NOT_MATCHING.message());
        }

        comment.setUser(null);
        comment.setCollage(null);

        collageRepository.removeCollageComment(comment);
    }

    public void likeCollage(long userId, long collageId){
        User user = userRepository.findUserById(userId);
        if(!userPrivilegesVerifier.isCurrentUser(user.getUsername())){
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

    public List<CollageLikeDTOGet> getCollageLikes(long collageId, int pageStart, int offset){
        return collageRepository.findCollageLikes(collageId, pageStart, offset)
                .stream()
                .map(like -> collageLikeMapper.toDTO(like))
                .toList();
    }

    public long getCollageLikesCount(long collageId){
        return collageRepository.findCollageLikesCount(collageId);
    }

    public void dislikeCollage(long userId, long collageId){
        CollageLike collageLike = collageRepository.findCollageLike(userId, collageId);
        if(!userPrivilegesVerifier.isCurrentUser(collageLike.getUser().getUsername())){
            throw new UserNotMatchingException(USER_NOT_MATCHING.message());
        }
        collageLike.setUser(null);
        collageLike.setCollage(null);
        collageRepository.removeCollageLike(collageLike);
    }

}
