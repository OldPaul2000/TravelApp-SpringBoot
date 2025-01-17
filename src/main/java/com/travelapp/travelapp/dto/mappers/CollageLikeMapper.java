package com.travelapp.travelapp.dto.mappers;

import com.travelapp.travelapp.dto.collagepost.CollageLikeDTOGet;
import com.travelapp.travelapp.dto.postedpictures.PostingUserDTOGet;
import com.travelapp.travelapp.model.usersposts.PostLike;
import org.springframework.stereotype.Component;

@Component
public class CollageLikeMapper {

    private PostingUserMapper userMapper;

    public CollageLikeMapper(PostingUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public CollageLikeDTOGet toDTO(PostLike postLike){

        PostingUserDTOGet userDTO = userMapper.toDTO(postLike.getUser());

        return new CollageLikeDTOGet(postLike.getId(),
                                     userDTO);
    }
}
