package com.travelapp.travelapp.dto.mappers;

import com.travelapp.travelapp.dto.postedpictures.PictureLikeDTOGet;
import com.travelapp.travelapp.dto.postedpictures.PostingUserDTOGet;
import com.travelapp.travelapp.model.postedpictures.PictureLike;
import com.travelapp.travelapp.model.userrelated.User;
import org.springframework.stereotype.Component;

@Component
public class PictureLikeMapper {

    public PictureLikeDTOGet toDTO(PictureLike like){
        User user = like.getUser();

        PostingUserDTOGet userDTO = new PostingUserDTOGet(
                user.getId(),
                user.getUserInfo().getFirstName(),
                user.getUserInfo().getLastName(),
                user.getUserInfo().getProfilePicture().getFileName()
        );

        return new PictureLikeDTOGet(
                like.getId(),
                userDTO
        );
    }

}
