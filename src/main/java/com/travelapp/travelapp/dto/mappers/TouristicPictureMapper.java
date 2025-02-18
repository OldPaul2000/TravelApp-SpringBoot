package com.travelapp.travelapp.dto.mappers;

import com.travelapp.travelapp.dto.places.PicturePlaceDTOGet;
import com.travelapp.travelapp.dto.postedpictures.PostingUserDTOGet;
import com.travelapp.travelapp.dto.postedpictures.TouristicPictureDTOGet;
import com.travelapp.travelapp.model.postedpictures.PicturePlace;
import com.travelapp.travelapp.model.postedpictures.TouristicPicture;
import com.travelapp.travelapp.model.userrelated.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TouristicPictureMapper {

    private PicturePlaceMapper picturePlaceMapper;
    private PostingUserMapper postingUserMapper;

    @Autowired
    public TouristicPictureMapper(PicturePlaceMapper picturePlaceMapper,
                                  PostingUserMapper postingUserMapper) {
        this.picturePlaceMapper = picturePlaceMapper;
        this.postingUserMapper = postingUserMapper;
    }

    public TouristicPictureDTOGet toDTO(TouristicPicture picture, byte[] fileBytes){
        User user = picture.getUser();
        PicturePlace picturePlace = picture.getPicturePlace();

        PostingUserDTOGet userDTO = postingUserMapper.toDTO(user);
        PicturePlaceDTOGet picturePlaceDTO = picturePlaceMapper.toDTO(picturePlace);

        return new TouristicPictureDTOGet(
                picture.getId(),
                picture.getFileName(),
                picture.getCaptureDateTime(),
                picture.getDescription(),
                picture.getCoordinates(),
                picture.getPictureLikes().size(),
                picture.getPictureComments().size(),
                picturePlaceDTO,
                userDTO,
                fileBytes
        );
    }
}

