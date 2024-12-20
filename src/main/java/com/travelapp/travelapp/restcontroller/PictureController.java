package com.travelapp.travelapp.restcontroller;

import com.travelapp.travelapp.dto.postedpictures.TouristicPictureDTOPost;
import com.travelapp.travelapp.model.postedpictures.TouristicPicture;
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
    public List<TouristicPicture> getAllPicturesByUser(@PathVariable("userId") int id){
        List<TouristicPicture> touristicPictures = pictureService.getTouristicPicturesByUser(id);
        return touristicPictures;
    }

    @GetMapping("/fromCity/{cityName}")
    public List<TouristicPicture> getAllPicturesByCity(@PathVariable String cityName){
        List<TouristicPicture> touristicPictures = pictureService.getTouristicPicturesByCity(cityName);
        return touristicPictures;
    }

    @GetMapping("/fromCommune/{communeName}")
    public List<TouristicPicture> getAllPicturesByCommune(@PathVariable String communeName){
        List<TouristicPicture> touristicPictures = pictureService.getTouristicPicturesByCommune(communeName);
        return touristicPictures;
    }

    @GetMapping("/fromVillage/{villageName}")
    public List<TouristicPicture> getAllPicturesByVillage(@PathVariable String villageName){
        List<TouristicPicture> touristicPictures = pictureService.getTouristicPicturesByVillage(villageName);
        return touristicPictures;
    }

    @GetMapping("/byPlaceName/{placeName}")
    public List<TouristicPicture> getAllPicturesByPlaceName(@PathVariable String placeName){
        List<TouristicPicture> touristicPictures = pictureService.getTouristicPicturesByPlaceName(placeName);
        return touristicPictures;
    }

    @PostMapping("/postPicture/{userId}")
    public ResponseEntity<String> postNewPicture(@PathVariable int userId, @RequestBody TouristicPictureDTOPost touristicPicture){
        pictureService.postNewPicture(userId, touristicPicture);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
