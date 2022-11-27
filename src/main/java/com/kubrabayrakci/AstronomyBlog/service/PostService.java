package com.kubrabayrakci.AstronomyBlog.service;

import com.kubrabayrakci.AstronomyBlog.model.Post;
import com.kubrabayrakci.AstronomyBlog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> getAllPosts(){

        return postRepository.findAll();
    }

    public void savePost(Post post){

        post.setDateCreated(LocalDateTime.now());
        postRepository.save(post);
    }


}
