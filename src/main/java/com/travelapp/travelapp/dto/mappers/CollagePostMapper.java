package com.travelapp.travelapp.dto.mappers;

import com.travelapp.travelapp.dto.collagepost.CollageDTOGet;
import com.travelapp.travelapp.dto.postedpictures.PostingUserDTOGet;
import com.travelapp.travelapp.dto.postedpictures.TouristicPictureDTOGet;
import com.travelapp.travelapp.model.usersposts.Collage;
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

    public CollageDTOGet toDTO(Collage collage){

        PostingUserDTOGet userDTO = userMapper.toDTO(collage.getUser());

        List<TouristicPictureDTOGet> picturesDTO = collage.getTouristicPictures()
                .stream()
                .map(picture -> {
                    return pictureMapper.toDTO(picture);
                }).toList();

        return new CollageDTOGet(
                collage.getId(),
                collage.getDescription(),
                collage.getDateTime(),
                userDTO,
                picturesDTO
        );
    }

}
