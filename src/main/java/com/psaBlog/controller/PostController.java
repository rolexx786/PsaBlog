package com.psaBlog.controller;

import com.psaBlog.payload.PostDto;
import com.psaBlog.service.PostService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Post")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping("/user/{uid}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto pdto, @PathVariable String uid){
        PostDto post = postService.createPost(pdto, uid);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
}
