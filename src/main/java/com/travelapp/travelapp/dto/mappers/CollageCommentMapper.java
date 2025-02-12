package com.travelapp.travelapp.dto.mappers;

import com.travelapp.travelapp.dto.collagepost.CollageCommentDTOGet;
import com.travelapp.travelapp.dto.postedpictures.PostingUserDTOGet;
import com.travelapp.travelapp.model.usersposts.CollageComment;
import org.springframework.stereotype.Component;

@Component
public class CollageCommentMapper {

    private PostingUserMapper userMapper;
    public CollageCommentMapper(PostingUserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public CollageCommentDTOGet toDTO(CollageComment comment){

        PostingUserDTOGet userDTO = userMapper.toDTO(comment.getUser());

        return new CollageCommentDTOGet(
                comment.getId(),
                comment.getComment(),
                comment.getDateTime(),
                userDTO,
                comment.isEdited()
        );
    }

}
