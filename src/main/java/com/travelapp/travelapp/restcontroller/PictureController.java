package com.travelapp.travelapp.restcontroller;

import com.travelapp.travelapp.dto.postedpictures.*;
import com.travelapp.travelapp.service.PictureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PictureController {

    private PictureService pictureService;
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    /* Works */
    @GetMapping("/users/{userId}/pictures")
    public List<TouristicPictureDTOGet> getAllPicturesByUser(@PathVariable("userId") long id){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByUser(id);

        return touristicPictures;
    }

    /* Works */
    @GetMapping("/cities/pictures")
    public List<TouristicPictureDTOGet> getAllPicturesByCity(@RequestParam String city){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByCity(city);

        return touristicPictures;
    }

    /* Works */
    @GetMapping("/communes/pictures")
    public List<TouristicPictureDTOGet> getAllPicturesByCommune(@RequestParam String commune){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByCommune(commune);

        return touristicPictures;
    }

    /* Works */
    @GetMapping("/villages/pictures")
    public List<TouristicPictureDTOGet> getAllPicturesByVillage(@RequestParam String village){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByVillage(village);

        return touristicPictures;
    }

    /* Works */
    @GetMapping("/place-names/pictures")
    public List<TouristicPictureDTOGet> getAllPicturesByPlaceName(@RequestParam String placeName){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByPlaceName(placeName);

        return touristicPictures;
    }

    /* Works */
    @PostMapping("/pictures/{userId}")
    public ResponseEntity<String> postNewPicture(@PathVariable long userId,
                                                 @RequestBody TouristicPictureDTOPost touristicPicture){
        pictureService.postNewPicture(userId, touristicPicture);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Works */
    @DeleteMapping("/pictures/{userId}/{pictureId}")
    public ResponseEntity<String> deletePicture(@PathVariable long userId,
                                                @PathVariable long pictureId){
        pictureService.deletePicture(userId, pictureId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Works */
    @PostMapping("/pictures/comments/{userId}/{pictureId}")
    public ResponseEntity<String> givePictureComment(@PathVariable long userId,
                                                     @PathVariable long pictureId,
                                                     @RequestBody PictureCommentDTOPost pictureComment){
        pictureService.postPictureComment(userId, pictureId, pictureComment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Works */
    @PutMapping("/pictures/comments/{userId}/{commentId}")
    public ResponseEntity<String> editPictureComment(@PathVariable long userId,
                                                     @PathVariable long commentId,
                                                     @RequestBody PictureCommentDTOPost pictureComment){
        pictureService.editPictureComment(userId, commentId, pictureComment);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /* Works */
    @GetMapping("/pictures/comments/{pictureId}")
    public List<PictureCommentDTOGet> getPictureComments(@PathVariable long pictureId){
        List<PictureCommentDTOGet> pictureComments = pictureService.getPictureComments(pictureId);

        return pictureComments;
    }

    /* Works */
    @GetMapping("/pictures/comments/count/{pictureId}")
    public Long getPictureCommentsCount(@PathVariable long pictureId){
        Long commentsCount = pictureService.getPictureCommentsCount(pictureId);

        return commentsCount;
    }

    /* Works */
    @DeleteMapping("/pictures/comments/{userId}/{commentId}")
    public ResponseEntity<String> deletePictureComment(@PathVariable long userId,
                                                       @PathVariable long commentId){
        pictureService.deletePictureComment(userId, commentId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Works */
    @PostMapping("/pictures/likes/{userId}/{pictureId}")
    public ResponseEntity<String> givePictureLike(@PathVariable long userId,
                                                  @PathVariable long pictureId){
        pictureService.likePicture(userId, pictureId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /* Works */
    @GetMapping("/pictures/likes/{pictureId}")
    public List<PictureLikeDTOGet> getPictureLikes(@PathVariable long pictureId){
        List<PictureLikeDTOGet> likes = pictureService.getPictureLikes(pictureId);

        return likes;
    }

    /* Works */
    @GetMapping("/pictures/likes/count/{pictureId}")
    public Long getLikesCount(@PathVariable long pictureId){
        Long likesCount = pictureService.getPictureLikesCount(pictureId);

        return likesCount;
    }

    /* Works */
    @DeleteMapping("/pictures/likes/{userId}/{pictureId}")
    public ResponseEntity<String> dislikePicture(@PathVariable long userId,
                                                @PathVariable long pictureId){
        pictureService.dislikePicture(userId, pictureId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
