package com.travelapp.travelapp.dto.mappers;

import com.travelapp.travelapp.dto.postedpictures.PostingUserDTOGet;
import com.travelapp.travelapp.model.userrelated.User;
import org.springframework.stereotype.Component;

@Component
public class PostingUserMapper {

    public PostingUserDTOGet toDTO(User user){

        return new PostingUserDTOGet(
                user.getId(),
                user.getUserInfo().getFirstName(),
                user.getUserInfo().getLastName(),
                user.getUserInfo().getProfilePicture().getFileName()
        );
    }

}
