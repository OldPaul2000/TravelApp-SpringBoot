package com.travelapp.travelapp.restcontroller;

import com.travelapp.travelapp.dto.postedpictures.*;
import com.travelapp.travelapp.service.PictureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PictureController {

    private PictureService pictureService;
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/pictures/{id}")
    public TouristicPictureDTOGet getPictureById(@PathVariable("id") long id){
        TouristicPictureDTOGet touristicPicture = pictureService.getTouristicPictureById(id);
        return touristicPicture;
    }

    @GetMapping("/users/{userId}/pictures")
    public List<TouristicPictureDTOGet> getAllPicturesByUser(@PathVariable("userId") long id,
                                                             @RequestParam int pageStart,
                                                             @RequestParam int offset){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByUser(id, pageStart, offset);

        return touristicPictures;
    }

    @GetMapping("/users/{userId}/place-types/pictures")
    public List<TouristicPictureDTOGet> getAllPicturesByUserAndPlaceType(@PathVariable("userId") long id,
                                                             @RequestParam String placeType,
                                                             @RequestParam int pageStart,
                                                             @RequestParam int offset){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByUserAndPlaceType(id, placeType, pageStart, offset);

        return touristicPictures;
    }

    @GetMapping("/cities/pictures")
    public List<TouristicPictureDTOGet> getAllPicturesByCity(@RequestParam String city,
                                                             @RequestParam int pageStart,
                                                             @RequestParam int offset){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByCity(city, pageStart, offset);

        return touristicPictures;
    }

    @GetMapping("/cities/place-types/pictures")
    public List<TouristicPictureDTOGet> getAllPicturesByCityAndPlaceType(@RequestParam String city,
                                                             @RequestParam String placeType,
                                                             @RequestParam int pageStart,
                                                             @RequestParam int offset){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByCityAndPlaceType(city, placeType, pageStart, offset);

        return touristicPictures;
    }

    @GetMapping("/communes/pictures")
    public List<TouristicPictureDTOGet> getAllPicturesByCommune(@RequestParam String commune,
                                                                @RequestParam int pageStart,
                                                                @RequestParam int offset){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByCommune(commune, pageStart, offset);

        return touristicPictures;
    }

    @GetMapping("/communes/place-types/pictures")
    public List<TouristicPictureDTOGet> getAllPicturesByCommuneAndPlaceType(@RequestParam String commune,
                                                                @RequestParam String placeType,
                                                                @RequestParam int pageStart,
                                                                @RequestParam int offset){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByCommuneAndPlaceType(commune, placeType, pageStart, offset);

        return touristicPictures;
    }

    @GetMapping("/villages/pictures")
    public List<TouristicPictureDTOGet> getAllPicturesByVillage(@RequestParam String village,
                                                                @RequestParam int pageStart,
                                                                @RequestParam int offset){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByVillage(village, pageStart, offset);

        return touristicPictures;
    }

    @GetMapping("/villages/place-types/pictures")
    public List<TouristicPictureDTOGet> getAllPicturesByVillageAndPlaceType(@RequestParam String village,
                                                                @RequestParam String placeType,
                                                                @RequestParam int pageStart,
                                                                @RequestParam int offset){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByVillageAndPlaceType(village, placeType, pageStart, offset);

        return touristicPictures;
    }

    @GetMapping("/place-names/pictures")
    public List<TouristicPictureDTOGet> getAllPicturesByPlaceName(@RequestParam String placeName,
                                                                  @RequestParam int pageStart,
                                                                  @RequestParam int offset){
        List<TouristicPictureDTOGet> touristicPictures = pictureService.getTouristicPicturesByPlaceName(placeName, pageStart, offset);

        return touristicPictures;
    }

    @PostMapping("/pictures/{userId}")
    public ResponseEntity<String> postNewPicture(@PathVariable long userId,
                                                 @RequestPart TouristicPictureDTOPost pictureInfo,
                                                 @RequestParam MultipartFile file){
        pictureService.postNewPicture(userId, pictureInfo, file);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/pictures/{userId}/{pictureId}")
    public ResponseEntity<String> deletePicture(@PathVariable long userId,
                                                @PathVariable long pictureId){
        pictureService.deletePicture(userId, pictureId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/pictures/comments/{userId}/{pictureId}")
    public ResponseEntity<String> givePictureComment(@PathVariable long userId,
                                                     @PathVariable long pictureId,
                                                     @RequestBody PictureCommentDTOPost comment){
        pictureService.postPictureComment(userId, pictureId, comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/pictures/comments/{userId}/{commentId}")
    public ResponseEntity<String> editPictureComment(@PathVariable long userId,
                                                     @PathVariable long commentId,
                                                     @RequestBody PictureCommentDTOPost comment){
        pictureService.editPictureComment(userId, commentId, comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/pictures/comments/{pictureId}")
    public List<PictureCommentDTOGet> getPictureComments(@PathVariable long pictureId,
                                                         @RequestParam int pageStart,
                                                         @RequestParam int offset){
        List<PictureCommentDTOGet> pictureComments = pictureService.getPictureComments(pictureId, pageStart, offset);

        return pictureComments;
    }

    @GetMapping("/pictures/comments/count/{pictureId}")
    public Long getPictureCommentsCount(@PathVariable long pictureId){
        Long commentsCount = pictureService.getPictureCommentsCount(pictureId);

        return commentsCount;
    }

    @DeleteMapping("/pictures/comments/{userId}/{commentId}")
    public ResponseEntity<String> deletePictureComment(@PathVariable long userId,
                                                       @PathVariable long commentId){
        pictureService.deletePictureComment(userId, commentId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/pictures/likes/{userId}/{pictureId}")
    public ResponseEntity<String> givePictureLike(@PathVariable long userId,
                                                  @PathVariable long pictureId){
        pictureService.likePicture(userId, pictureId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/pictures/likes/{pictureId}")
    public List<PictureLikeDTOGet> getPictureLikes(@PathVariable long pictureId,
                                                   @RequestParam int pageStart,
                                                   @RequestParam int offset){
        List<PictureLikeDTOGet> likes = pictureService.getPictureLikes(pictureId, pageStart, offset);

        return likes;
    }

    @GetMapping("/pictures/likes/count/{pictureId}")
    public Long getLikesCount(@PathVariable long pictureId){
        Long likesCount = pictureService.getPictureLikesCount(pictureId);

        return likesCount;
    }

    @DeleteMapping("/pictures/likes/{userId}/{pictureId}")
    public ResponseEntity<String> dislikePicture(@PathVariable long userId,
                                                @PathVariable long pictureId){
        pictureService.dislikePicture(userId, pictureId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
