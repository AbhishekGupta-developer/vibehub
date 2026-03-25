package com.myorganisation.vibehub.service;

import com.myorganisation.vibehub.dto.request.PostRequestDto;
import com.myorganisation.vibehub.dto.response.GenericResponseDto;
import com.myorganisation.vibehub.model.Post;
import com.myorganisation.vibehub.model.User;
import com.myorganisation.vibehub.repository.PostRepository;
import com.myorganisation.vibehub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public GenericResponseDto uploadPost(PostRequestDto postRequestDto) {
        User user = userRepository.findById(postRequestDto.getUserId()).orElse(null);

        GenericResponseDto genericResponseDto = new GenericResponseDto();

        if(user == null) {
            genericResponseDto.setIsSuccess(false);
            genericResponseDto.setMessage("User id: " + postRequestDto.getUserId() + " doesn't exist");
        }

        if(postRequestDto.getCaption() == null ||postRequestDto.getCaption().isBlank()) {
            genericResponseDto.setIsSuccess(false);
            genericResponseDto.setMessage("Caption can not be blank or null");
        }

        Post post = new Post();
        post.setCaption(postRequestDto.getCaption());
        post.setUser(user);

        postRepository.save(post);

        genericResponseDto.setIsSuccess(true);
        genericResponseDto.setMessage("Posted successfully");

        return genericResponseDto;
    }

    @Override
    public GenericResponseDto likePost(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        GenericResponseDto genericResponseDto = new GenericResponseDto();

        if(post == null) {
            genericResponseDto.setIsSuccess(false);
            genericResponseDto.setMessage("Post id: " + postId + " doesn't exist");
        }

        post.setLikeCount(post.getLikeCount() + 1);
        postRepository.save(post);

        genericResponseDto.setIsSuccess(true);
        genericResponseDto.setMessage("post like + 1");

        return genericResponseDto;
    }
}
