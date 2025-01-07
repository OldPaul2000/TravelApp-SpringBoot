package com.travelapp.travelapp.service;

import com.travelapp.travelapp.repository.PictureRepository;
import com.travelapp.travelapp.repository.PostRepository;
import com.travelapp.travelapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private PictureRepository pictureRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, PictureRepository pictureRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
    }
}
