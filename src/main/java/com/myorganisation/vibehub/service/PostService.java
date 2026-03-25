package com.myorganisation.vibehub.service;

import com.myorganisation.vibehub.dto.request.PostRequestDto;
import com.myorganisation.vibehub.dto.response.GenericResponseDto;

public interface PostService {
    GenericResponseDto uploadPost(PostRequestDto postRequestDto);
    GenericResponseDto likePost(Long postId);
}
