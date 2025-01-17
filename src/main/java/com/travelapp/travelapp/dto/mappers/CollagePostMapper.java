package com.travelapp.travelapp.dto.mappers;

import com.travelapp.travelapp.dto.collagepost.CollagePostDTOGet;
import com.travelapp.travelapp.dto.postedpictures.PostingUserDTOGet;
import com.travelapp.travelapp.dto.postedpictures.TouristicPictureDTOGet;
import com.travelapp.travelapp.model.usersposts.CollagePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollagePostMapper {

    private PostingUserMapper userMapper;
    private TouristicPictureMapper pictureMapper;

    @Autowired
    public CollagePostMapper(PostingUserMapper userMapper,
                             TouristicPictureMapper pictureMapper) {
        this.userMapper = userMapper;
        this.pictureMapper = pictureMapper;
    }

    public CollagePostDTOGet toDTO(CollagePost collagePost){

        PostingUserDTOGet userDTO = userMapper.toDTO(collagePost.getUser());

        List<TouristicPictureDTOGet> picturesDTO = collagePost.getTouristicPictures()
                .stream()
                .map(picture -> {
                    return pictureMapper.toDTO(picture);
                }).toList();

        return new CollagePostDTOGet(
                collagePost.getId(),
                collagePost.getDescription(),
                collagePost.getDateTime(),
                userDTO,
                picturesDTO
        );
    }

}
