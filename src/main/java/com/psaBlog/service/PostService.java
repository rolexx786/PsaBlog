package com.psaBlog.service;

import com.psaBlog.entity.Post;
import com.psaBlog.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto pdto, String userId);
    PostDto updatePost(PostDto pdto, String id);
    List<PostDto> getAllPost();
    PostDto getPostById(String post);
    void deletePost(String id);

    List<PostDto> getPostByUser(String userId);
}
