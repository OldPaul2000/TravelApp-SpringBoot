package com.travelapp.travelapp.dto.mappers;

import com.travelapp.travelapp.dto.collagepost.CollageDTOGet;
import com.travelapp.travelapp.dto.postedpictures.PostingUserDTOGet;
import com.travelapp.travelapp.dto.postedpictures.TouristicPictureDTOGet;
import com.travelapp.travelapp.model.usersposts.Collage;
import com.travelapp.travelapp.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollageMapper {

    private PostingUserMapper userMapper;
    private TouristicPictureMapper pictureMapper;
    private FileStorageService fileStorageService;

    @Autowired
    public CollageMapper(PostingUserMapper userMapper,
                         TouristicPictureMapper pictureMapper,
                         FileStorageService fileStorageService) {
        this.userMapper = userMapper;
        this.pictureMapper = pictureMapper;
        this.fileStorageService = fileStorageService;
    }

    @Value("${app.files.touristic-pictures}")
    private String TOURISTIC_PICTURES_LOCATION;

    public CollageDTOGet toDTO(Collage collage){

        PostingUserDTOGet userDTO = userMapper.toDTO(collage.getUser());

        List<TouristicPictureDTOGet> picturesDTO = collage.getTouristicPictures()
                .stream()
                .map(picture -> {
                    byte[] fileBytes = fileStorageService
                            .getFileBytes(picture.getUser().getId(),
                                          TOURISTIC_PICTURES_LOCATION,
                                          picture.getFileName());
                    return pictureMapper.toDTO(picture, fileBytes);
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
