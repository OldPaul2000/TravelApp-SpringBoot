package com.travelapp.travelapp.dto.mappers;

import com.travelapp.travelapp.dto.postedpictures.PictureCommentDTOGet;
import com.travelapp.travelapp.dto.postedpictures.PostingUserDTOGet;
import com.travelapp.travelapp.model.postedpictures.PictureComment;
import com.travelapp.travelapp.model.userrelated.User;
import org.springframework.stereotype.Component;

@Component
public class PictureCommentMapper {

    public PictureCommentDTOGet toDTO(PictureComment comment){
        User user = comment.getUser();

        PostingUserDTOGet userDTO = new PostingUserDTOGet(
                user.getId(),
                user.getUserInfo().getFirstName(),
                user.getUserInfo().getLastName(),
                user.getUserInfo().getProfilePicture().getFileName()
        );

        return new PictureCommentDTOGet(
                comment.getId(),
                comment.getComment(),
                comment.getDateTime(),
                userDTO
        );
    }

}
