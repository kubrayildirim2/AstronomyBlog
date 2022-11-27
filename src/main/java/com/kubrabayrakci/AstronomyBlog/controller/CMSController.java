package com.kubrabayrakci.AstronomyBlog.controller;

import com.kubrabayrakci.AstronomyBlog.model.Post;
import com.kubrabayrakci.AstronomyBlog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cms")
public class CMSController {

    @Autowired
    PostService postService;

    @GetMapping
    public String getCMSPage(){
        return "cmsHome";
    }

    @GetMapping("/create")
    public String getCreatePage(Model model){

        Post post = new Post();
        model.addAttribute("newPost", post);

        return "createPost";
    }


    @PostMapping("/create")
    public String createNewPost(@ModelAttribute("newPost") Post post){

        postService.savePost(post);

        return "redirect:/cms";
    }

    @GetMapping("/showpost")
    public String showThePosts(Model model){

        List<Post> allPosts = postService.getAllPosts();
        model.addAttribute("allPosts", allPosts);

        return "posts";

    }


}
