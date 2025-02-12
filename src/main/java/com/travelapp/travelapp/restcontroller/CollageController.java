package com.travelapp.travelapp.restcontroller;

import com.travelapp.travelapp.dto.collagepost.*;
import com.travelapp.travelapp.service.CollageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/collages")

public class CollageController {

    private CollageService collageService;
    public CollageController(CollageService collageService) {
        this.collageService = collageService;
    }

    /* Works */
    @PostMapping("/{userId}")
    public ResponseEntity<String> postNewCollage(@PathVariable long userId,
                                                 @RequestBody CollageDTOPost collagePost){
        collageService.postNewCollage(userId, collagePost);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /* Works */
    @DeleteMapping("/{userId}/{collageId}")
    public ResponseEntity<String> deleteCollage(@PathVariable long userId,
                                                @PathVariable long collageId){
        collageService.deleteCollage(userId, collageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Works */
    @GetMapping("/{id}")
    public CollageDTOGet getCollagePost(@PathVariable long id){
        CollageDTOGet collage = collageService.getCollageById(id);
        return collage;
    }

    /* Works */
    @PostMapping("/comments/{userId}/{collageId}")
    public ResponseEntity<String> postCollageComment(@PathVariable long userId,
                                                     @PathVariable long collageId,
                                                     @RequestBody CollageCommentDTOPost collageComment){
        collageService.postCollageComment(userId, collageId, collageComment);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /* Works */
    @PutMapping("/comments/{userId}/{commentId}")
    public ResponseEntity<String> editCollageComment(@PathVariable long userId,
                                                     @PathVariable long commentId,
                                                     @RequestBody CollageCommentDTOPost collageComment){
        collageService.editCollageComment(userId, commentId, collageComment);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Works */
    @DeleteMapping("/comments/{userId}/{commentId}")
    public ResponseEntity<String> deleteCollageComment(@PathVariable long userId,
                                                       @PathVariable long commentId){
        collageService.deleteCollageComment(userId, commentId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Works */
    @GetMapping("/comments/{collageId}")
    public List<CollageCommentDTOGet> getCollageComments(@PathVariable long collageId){
        return collageService.getCollageComments(collageId);
    }

    /* Works */
    @GetMapping("/comments/count/{collageId}")
    public Long getPostCommentsCount(@PathVariable long collageId){
        return collageService.getCollageCommentsCount(collageId);
    }

    /* Works */
    @PostMapping("/likes/{userId}/{collageId}")
    public ResponseEntity<String> likeCollage(@PathVariable long userId,
                                              @PathVariable long collageId){
        collageService.likeCollage(userId, collageId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /* Works */
    @DeleteMapping("/likes/{userId}/{collageId}")
    public ResponseEntity<String> dislikeCollage(@PathVariable long userId,
                                                 @PathVariable long collageId){
        collageService.dislikeCollage(userId, collageId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* Works */
    @GetMapping("/{collageId}/likes")
    public List<CollageLikeDTOGet> getCollageLikes(@PathVariable long collageId){
        return collageService.getCollageLikes(collageId);
    }

    /* Works */
    @GetMapping("/{collageId}/likes/count/")
    public long getCollageLikesCount(@PathVariable long collageId){
        return collageService.getCollageLikesCount(collageId);
    }

}
