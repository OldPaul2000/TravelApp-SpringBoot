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

    @PostMapping("/{userId}")
    public ResponseEntity<String> postNewCollage(@PathVariable long userId,
                                                 @RequestBody CollageDTOPost collagePost){
        collageService.postNewCollage(userId, collagePost);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}/{collageId}")
    public ResponseEntity<String> deleteCollage(@PathVariable long userId,
                                                @PathVariable long collageId){
        collageService.deleteCollage(userId, collageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public CollageDTOGet getCollage(@PathVariable long id){
        CollageDTOGet collage = collageService.getCollageById(id);
        return collage;
    }

    @PostMapping("/comments/{userId}/{collageId}")
    public ResponseEntity<String> postCollageComment(@PathVariable long userId,
                                                     @PathVariable long collageId,
                                                     @RequestBody CollageCommentDTOPost comment){
        collageService.postCollageComment(userId, collageId, comment);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/comments/{userId}/{commentId}")
    public ResponseEntity<String> editCollageComment(@PathVariable long userId,
                                                     @PathVariable long commentId,
                                                     @RequestBody CollageCommentDTOPost comment){
        collageService.editCollageComment(userId, commentId, comment);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/comments/{userId}/{commentId}")
    public ResponseEntity<String> deleteCollageComment(@PathVariable long userId,
                                                       @PathVariable long commentId){
        collageService.deleteCollageComment(userId, commentId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/comments/{collageId}")
    public List<CollageCommentDTOGet> getCollageComments(@PathVariable long collageId){
        return collageService.getCollageComments(collageId);
    }

    @GetMapping("/comments/count/{collageId}")
    public Long getCollageCommentsCount(@PathVariable long collageId){
        return collageService.getCollageCommentsCount(collageId);
    }

    @PostMapping("/likes/{userId}/{collageId}")
    public ResponseEntity<String> likeCollage(@PathVariable long userId,
                                              @PathVariable long collageId){
        collageService.likeCollage(userId, collageId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/likes/{userId}/{collageId}")
    public ResponseEntity<String> dislikeCollage(@PathVariable long userId,
                                                 @PathVariable long collageId){
        collageService.dislikeCollage(userId, collageId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/likes/{collageId}")
    public List<CollageLikeDTOGet> getCollageLikes(@PathVariable long collageId){
        return collageService.getCollageLikes(collageId);
    }

    @GetMapping("/likes/count/{collageId}")
    public long getCollageLikesCount(@PathVariable long collageId){
        return collageService.getCollageLikesCount(collageId);
    }

}
