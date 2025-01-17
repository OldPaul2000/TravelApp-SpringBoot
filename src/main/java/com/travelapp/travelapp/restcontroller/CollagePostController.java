package com.travelapp.travelapp.restcontroller;

import com.travelapp.travelapp.dto.collagepost.*;
import com.travelapp.travelapp.service.CollagePostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/collage")

public class CollagePostController {

    private CollagePostService collagePostService;
    public CollagePostController(CollagePostService collagePostService) {
        this.collagePostService = collagePostService;
    }

    @GetMapping("/byId/{id}")
    public CollagePostDTOGet getCollagePosts(@PathVariable int id){
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        CollagePostDTOGet collage = collagePostService.getCollageById(id);
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        return collage;
    }

    @GetMapping("/fromUser/{id}")
    public List<CollagePostDTOGet> getCollagesFromUser(@PathVariable long id){
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        List<CollagePostDTOGet> collages = collagePostService.getCollagesFromUser(id);
        System.out.println("\u001b[31m" + "========================================================================" + "\u001B[0m");
        return collages;
    }

    @PostMapping("/newCollage")
    public ResponseEntity<String> postNewCollage(@RequestBody CollagePostDTOPost collagePost){
        collagePostService.postNewCollage(collagePost);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCollage/{userId}/{collageId}")
    public ResponseEntity<String> deleteCollage(@PathVariable long userId,
                                                @PathVariable long collageId){
        collagePostService.deleteCollage(userId, collageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/comment/{userId}/{collageId}")
    public ResponseEntity<String> postCollageComment(@PathVariable long userId,
                                                     @PathVariable long collageId,
                                                     @RequestBody CollageCommentDTOPost collageComment){
        collagePostService.postCollageComment(userId, collageId, collageComment);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/removeComment/{userId}/{commentId}")
    public ResponseEntity<String> deleteCollageComment(@PathVariable long userId,
                                                       @PathVariable long commentId){
        collagePostService.deleteCollageComment(userId, commentId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/comments/{collageId}")
    public List<CollageCommentDTOGet> getCollageComments(@PathVariable long collageId){
        return collagePostService.getCollageComments(collageId);
    }

    @GetMapping("/commentsCount/{collageId}")
    public Long getPostCommentsCount(@PathVariable long collageId){
        return collagePostService.getCollageCommentsCount(collageId);
    }

    @PostMapping("/likeCollage/{userId}/{collageId}")
    public ResponseEntity<String> likeCollage(@PathVariable long userId,
                                              @PathVariable long collageId){
        collagePostService.likeCollage(userId, collageId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/dislikeCollage/{userId}/{collageId}")
    public ResponseEntity<String> dislikeCollage(@PathVariable long userId,
                                                 @PathVariable long collageId){
        collagePostService.dislikeCollage(userId, collageId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/collageLikes/{collageId}")
    public List<CollageLikeDTOGet> getCollageLikes(@PathVariable long collageId){
        return collagePostService.getCollageLikes(collageId);
    }

    @GetMapping("/collageLikesCount/{collageId}")
    public long getCollageLikesCount(@PathVariable long collageId){
        return collagePostService.getCollagePostLikesCount(collageId);
    }

}
