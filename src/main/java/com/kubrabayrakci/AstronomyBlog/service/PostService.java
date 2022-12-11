package com.kubrabayrakci.AstronomyBlog.service;

import com.kubrabayrakci.AstronomyBlog.model.Post;
import com.kubrabayrakci.AstronomyBlog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;


    public List<Post> getAllPosts() {

        return postRepository.findAll();

    }

    public void savePost(Post post) {

        if (post.getId()==null) {
            post.setDateCreated(LocalDateTime.now());
        }
            postRepository.save(post);
    }

    public boolean deletePost(Long id) {

        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            postRepository.delete(post);
            return true;
        }
        return false;
    }

    public Post findThePostById(Long id) {

        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            return post;
        }
        return null;
    }


}
