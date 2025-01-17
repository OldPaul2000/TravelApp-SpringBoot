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
    public List<TouristicPictureDTOGet> getAllPicturesByUser(@PathVariable("userId") int id){
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByUser(id);
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");

        return touristicPictures;
    }

    @GetMapping("/fromCity/{cityName}")
    public List<TouristicPictureDTOGet> getAllPicturesByCity(@PathVariable String cityName){
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByCity(cityName);
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        return touristicPictures;
    }

    @GetMapping("/fromCommune/{communeName}")
    public List<TouristicPictureDTOGet> getAllPicturesByCommune(@PathVariable String communeName){
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByCommune(communeName);
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        return touristicPictures;
    }

    @GetMapping("/fromVillage/{villageName}")
    public List<TouristicPictureDTOGet> getAllPicturesByVillage(@PathVariable String villageName){
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByVillage(villageName);
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        return touristicPictures;
    }

    @GetMapping("/byPlaceName/{placeName}")
    public List<TouristicPictureDTOGet> getAllPicturesByPlaceName(@PathVariable String placeName){
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByPlaceName(placeName);
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        return touristicPictures;
    }

    @PostMapping("/picture/{userId}")
    public ResponseEntity<String> postNewPicture(@PathVariable int userId, @RequestBody TouristicPictureDTOPost touristicPicture){
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
    public ResponseEntity<String> givePictureComment(@PathVariable int userId,
                                                     @PathVariable int pictureId,
                                                     @RequestBody PictureCommentDTOPost pictureComment){
        pictureService.postPictureComment(userId, pictureId, pictureComment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/pictureComments/{pictureId}")
    public List<PictureCommentDTOGet> getPictureComments(@PathVariable int pictureId){
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        List<PictureCommentDTOGet> pictureComments = pictureService.getPictureComments(pictureId);
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        return pictureComments;
    }

    @GetMapping("/pictureCommentsCount/{pictureId}")
    public Long getPictureCommentsCount(@PathVariable int pictureId){
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        Long commentsCount = pictureService.getPictureCommentsCount(pictureId);
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        return commentsCount;
    }

    @DeleteMapping("/deletePictureComment/{userId}/{commentId}")
    public ResponseEntity<String> deletePictureComment(@PathVariable int userId,
                                                       @PathVariable int commentId){
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        pictureService.deletePictureComment(userId, commentId);
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/likePicture/{userId}/{pictureId}")
    public ResponseEntity<String> givePictureLike(@PathVariable int userId,
                                                  @PathVariable int pictureId){
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        pictureService.likePicture(userId, pictureId);
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/pictureLikes/{pictureId}")
    public List<PictureLikeDTOGet> getPictureLikes(@PathVariable int pictureId){
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        List<PictureLikeDTOGet> likes = pictureService.getPictureLikes(pictureId);
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        return likes;
    }

    @GetMapping("/pictureLikesCount/{pictureId}")
    public Long getLikesCount(@PathVariable int pictureId){
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        Long likesCount = pictureService.getPictureLikesCount(pictureId);
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        return likesCount;
    }

    @DeleteMapping("deletePictureLike/{userId}/{pictureId}")
    public ResponseEntity<String> unlikePicture(@PathVariable int userId,
                                                @PathVariable int pictureId){
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        pictureService.dislikePicture(userId, pictureId);
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
