package com.travelapp.travelapp.restcontroller;

import com.travelapp.travelapp.dto.collagepost.*;
import com.travelapp.travelapp.service.CollageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/collage")

public class CollageController {

    private CollageService collageService;
    public CollageController(CollageService collageService) {
        this.collageService = collageService;
    }

    @PostMapping("/newCollage")
    public ResponseEntity<String> postNewCollage(@RequestBody CollageDTOPost collagePost){
        collageService.postNewCollage(collagePost);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCollage/{userId}/{collageId}")
    public ResponseEntity<String> deleteCollage(@PathVariable long userId,
                                                @PathVariable long collageId){
        collageService.deleteCollage(userId, collageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/byId/{id}")
    public CollageDTOGet getCollagePost(@PathVariable int id){
        CollageDTOGet collage = collageService.getCollageById(id);
        return collage;
    }

    @GetMapping("/fromUser/{id}")
    public List<CollageDTOGet> getCollagesFromUser(@PathVariable long id){
        List<CollageDTOGet> collages = collageService.getCollagesFromUser(id);
        return collages;
    }


    @PostMapping("/comment/{userId}/{collageId}")
    public ResponseEntity<String> postCollageComment(@PathVariable long userId,
                                                     @PathVariable long collageId,
                                                     @RequestBody CollageCommentDTOPost collageComment){
        collageService.postCollageComment(userId, collageId, collageComment);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/removeComment/{userId}/{commentId}")
    public ResponseEntity<String> deleteCollageComment(@PathVariable long userId,
                                                       @PathVariable long commentId){
        collageService.deleteCollageComment(userId, commentId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/comments/{collageId}")
    public List<CollageCommentDTOGet> getCollageComments(@PathVariable long collageId){
        return collageService.getCollageComments(collageId);
    }

    @GetMapping("/commentsCount/{collageId}")
    public Long getPostCommentsCount(@PathVariable long collageId){
        return collageService.getCollageCommentsCount(collageId);
    }

    @PostMapping("/likeCollage/{userId}/{collageId}")
    public ResponseEntity<String> likeCollage(@PathVariable long userId,
                                              @PathVariable long collageId){
        collageService.likeCollage(userId, collageId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/dislikeCollage/{userId}/{collageId}")
    public ResponseEntity<String> dislikeCollage(@PathVariable long userId,
                                                 @PathVariable long collageId){
        collageService.dislikeCollage(userId, collageId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/collageLikes/{collageId}")
    public List<CollageLikeDTOGet> getCollageLikes(@PathVariable long collageId){
        return collageService.getCollageLikes(collageId);
    }

    @GetMapping("/collageLikesCount/{collageId}")
    public long getCollageLikesCount(@PathVariable long collageId){
        return collageService.getCollageLikesCount(collageId);
    }

}
