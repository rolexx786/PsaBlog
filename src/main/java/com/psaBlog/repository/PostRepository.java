package com.psaBlog.repository;

import com.psaBlog.entity.Post;
import com.psaBlog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {
    List<Post> findByUser(User user);


}