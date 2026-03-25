package com.myorganisation.vibehub.controller;

import com.myorganisation.vibehub.dto.request.PostRequestDto;
import com.myorganisation.vibehub.dto.response.GenericResponseDto;
import com.myorganisation.vibehub.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<GenericResponseDto> uploadPost(@RequestBody PostRequestDto postRequestDto) {
        return new ResponseEntity<>(postService.uploadPost(postRequestDto), HttpStatusCode.valueOf(201));
    }

    @PostMapping("/like/{postId}")
    public ResponseEntity<GenericResponseDto> likePost(@PathVariable Long postId) {
        return new ResponseEntity<>(postService.likePost(postId), HttpStatusCode.valueOf(201));
    }
}
