package com.travelapp.travelapp.restcontroller;

import com.travelapp.travelapp.dto.postedpictures.*;
import com.travelapp.travelapp.service.PictureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pictures")
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
    @GetMapping("/cities/{city}/pictures")
    public List<TouristicPictureDTOGet> getAllPicturesByCity(@PathVariable String city){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByCity(city);

        return touristicPictures;
    }

    /* Works */
    @GetMapping("/communes/{commune}/pictures")
    public List<TouristicPictureDTOGet> getAllPicturesByCommune(@PathVariable String commune){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByCommune(commune);

        return touristicPictures;
    }

    /* Works */
    @GetMapping("/villages/{village}/pictures")
    public List<TouristicPictureDTOGet> getAllPicturesByVillage(@PathVariable String village){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByVillage(village);

        return touristicPictures;
    }

    /* Works */
    @GetMapping("/place-names/{placeName}/pictures")
    public List<TouristicPictureDTOGet> getAllPicturesByPlaceName(@PathVariable String placeName){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByPlaceName(placeName);

        return touristicPictures;
    }

    /* Works */
    @PostMapping("/{userId}")
    public ResponseEntity<String> postNewPicture(@PathVariable long userId,
                                                 @RequestBody TouristicPictureDTOPost touristicPicture){
        pictureService.postNewPicture(userId, touristicPicture);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Works */
    @DeleteMapping("/{userId}/{pictureId}")
    public ResponseEntity<String> deletePicture(@PathVariable long userId,
                                                @PathVariable long pictureId){
        pictureService.deletePicture(userId, pictureId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Works */
    @PostMapping("/comments/{userId}/{pictureId}")
    public ResponseEntity<String> givePictureComment(@PathVariable long userId,
                                                     @PathVariable long pictureId,
                                                     @RequestBody PictureCommentDTOPost pictureComment){
        pictureService.postPictureComment(userId, pictureId, pictureComment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Works */
    @GetMapping("/comments/{pictureId}")
    public List<PictureCommentDTOGet> getPictureComments(@PathVariable long pictureId){
        List<PictureCommentDTOGet> pictureComments = pictureService.getPictureComments(pictureId);

        return pictureComments;
    }

    /* Works */
    @GetMapping("/comments/count/{pictureId}")
    public Long getPictureCommentsCount(@PathVariable long pictureId){
        Long commentsCount = pictureService.getPictureCommentsCount(pictureId);

        return commentsCount;
    }

    /* Works */
    @DeleteMapping("/comments/{userId}/{commentId}")
    public ResponseEntity<String> deletePictureComment(@PathVariable long userId,
                                                       @PathVariable long commentId){
        pictureService.deletePictureComment(userId, commentId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Works */
    @PostMapping("/likes/{userId}/{pictureId}")
    public ResponseEntity<String> givePictureLike(@PathVariable long userId,
                                                  @PathVariable long pictureId){
        pictureService.likePicture(userId, pictureId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /* Works */
    @GetMapping("/likes/{pictureId}")
    public List<PictureLikeDTOGet> getPictureLikes(@PathVariable long pictureId){
        List<PictureLikeDTOGet> likes = pictureService.getPictureLikes(pictureId);

        return likes;
    }

    /* Works */
    @GetMapping("/likes/count/{pictureId}")
    public Long getLikesCount(@PathVariable long pictureId){
        Long likesCount = pictureService.getPictureLikesCount(pictureId);

        return likesCount;
    }

    /* Works */
    @DeleteMapping("/likes/{userId}/{pictureId}")
    public ResponseEntity<String> dislikePicture(@PathVariable long userId,
                                                @PathVariable long pictureId){
        pictureService.dislikePicture(userId, pictureId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
