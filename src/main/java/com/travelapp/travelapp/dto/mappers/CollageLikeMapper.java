package com.travelapp.travelapp.dto.mappers;

import com.travelapp.travelapp.dto.collagepost.CollageLikeDTOGet;
import com.travelapp.travelapp.dto.postedpictures.PostingUserDTOGet;
import com.travelapp.travelapp.model.usersposts.CollageLike;
import org.springframework.stereotype.Component;

@Component
public class CollageLikeMapper {

    private PostingUserMapper userMapper;

    public CollageLikeMapper(PostingUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public CollageLikeDTOGet toDTO(CollageLike collageLike){

        PostingUserDTOGet userDTO = userMapper.toDTO(collageLike.getUser());

        return new CollageLikeDTOGet(collageLike.getId(),
                                     userDTO);
    }
}
