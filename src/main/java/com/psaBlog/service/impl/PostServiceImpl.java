package com.psaBlog.service.impl;

import com.psaBlog.entity.Post;
import com.psaBlog.entity.User;
import com.psaBlog.exception.ResourceNotFound;
import com.psaBlog.payload.PostDto;
import com.psaBlog.repository.PostRepository;
import com.psaBlog.repository.UserRepository;
import com.psaBlog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto pdto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User Not found with id: " + userId));


        Post map = modelMapper.map(pdto, Post.class);
        map.setUser(user);
        String string = UUID.randomUUID().toString();
        map.setId(string);
        Post save = postRepository.save(map);
        return modelMapper.map(save,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto pdto, String id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Post not found with id: " + id));
        post.setTitle(pdto.getTitle());
        post.setContent(pdto.getContent());
        post.setGenre(pdto.getGenre());
        Post updatedPost = postRepository.save(post);
        return modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> all = postRepository.findAll();
        List<PostDto> collect = all.stream().map((element) -> modelMapper.map(element, PostDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public PostDto getPostById(String post) {
        Post p = postRepository.findById(post).orElseThrow(() -> new ResourceNotFound("Post not found with id: "+post));
        return modelMapper.map(p,PostDto.class);
    }

    @Override
    public void deletePost(String id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Post not found with id: " + id));
        postRepository.deleteById(id);

    }

    @Override
    public List<PostDto> getPostByUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User Not Found With id: " + userId));
        List<Post> byUser = postRepository.findByUser(user);
        List<PostDto> collect = byUser.stream().map((element) -> modelMapper.map(element, PostDto.class)).collect(Collectors.toList());

        return collect;
    }

}
