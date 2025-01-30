package com.travelapp.travelapp.restcontroller;

import com.travelapp.travelapp.dto.postedpictures.*;
import com.travelapp.travelapp.service.PictureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/touristicPictures")
public class PictureController {

    private PictureService pictureService;
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/fromUser/{userId}")
    public List<TouristicPictureDTOGet> getAllPicturesByUser(@PathVariable("userId") long id){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByUser(id);

        return touristicPictures;
    }

    @GetMapping("/fromCity/{cityName}")
    public List<TouristicPictureDTOGet> getAllPicturesByCity(@PathVariable String cityName){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByCity(cityName);

        return touristicPictures;
    }

    @GetMapping("/fromCommune/{communeName}")
    public List<TouristicPictureDTOGet> getAllPicturesByCommune(@PathVariable String communeName){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByCommune(communeName);

        return touristicPictures;
    }

    @GetMapping("/fromVillage/{villageName}")
    public List<TouristicPictureDTOGet> getAllPicturesByVillage(@PathVariable String villageName){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByVillage(villageName);

        return touristicPictures;
    }

    @GetMapping("/byPlaceName/{placeName}")
    public List<TouristicPictureDTOGet> getAllPicturesByPlaceName(@PathVariable String placeName){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByPlaceName(placeName);

        return touristicPictures;
    }

    @PostMapping("/picture/{userId}")
    public ResponseEntity<String> postNewPicture(@PathVariable long userId,
                                                 @RequestBody TouristicPictureDTOPost touristicPicture){
        pictureService.postNewPicture(userId, touristicPicture);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deletePicture/{userId}/{pictureId}")
    public ResponseEntity<String> deletePicture(@PathVariable long userId,
                                                @PathVariable long pictureId){
        pictureService.deletePicture(userId, pictureId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/comments/{userId}/{pictureId}")
    public ResponseEntity<String> givePictureComment(@PathVariable long userId,
                                                     @PathVariable long pictureId,
                                                     @RequestBody PictureCommentDTOPost pictureComment){
        pictureService.postPictureComment(userId, pictureId, pictureComment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/pictureComments/{pictureId}")
    public List<PictureCommentDTOGet> getPictureComments(@PathVariable long pictureId){
        List<PictureCommentDTOGet> pictureComments = pictureService.getPictureComments(pictureId);

        return pictureComments;
    }

    @GetMapping("/pictureCommentsCount/{pictureId}")
    public Long getPictureCommentsCount(@PathVariable long pictureId){
        Long commentsCount = pictureService.getPictureCommentsCount(pictureId);

        return commentsCount;
    }

    @DeleteMapping("/deletePictureComment/{userId}/{commentId}")
    public ResponseEntity<String> deletePictureComment(@PathVariable long userId,
                                                       @PathVariable long commentId){
        pictureService.deletePictureComment(userId, commentId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/likePicture/{userId}/{pictureId}")
    public ResponseEntity<String> givePictureLike(@PathVariable long userId,
                                                  @PathVariable long pictureId){
        pictureService.likePicture(userId, pictureId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/pictureLikes/{pictureId}")
    public List<PictureLikeDTOGet> getPictureLikes(@PathVariable long pictureId){
        List<PictureLikeDTOGet> likes = pictureService.getPictureLikes(pictureId);

        return likes;
    }

    @GetMapping("/pictureLikesCount/{pictureId}")
    public Long getLikesCount(@PathVariable long pictureId){
        Long likesCount = pictureService.getPictureLikesCount(pictureId);

        return likesCount;
    }

    @DeleteMapping("deletePictureLike/{userId}/{pictureId}")
    public ResponseEntity<String> dislikePicture(@PathVariable long userId,
                                                @PathVariable long pictureId){
        pictureService.dislikePicture(userId, pictureId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
