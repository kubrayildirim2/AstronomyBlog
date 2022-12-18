package com.kubrabayrakci.AstronomyBlog.controller;

import com.kubrabayrakci.AstronomyBlog.model.Post;
import com.kubrabayrakci.AstronomyBlog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    PostService postService;

    @GetMapping
    public String getHomePage(Model model) {
        List<Post> allPosts = postService.getAllPosts();
        model.addAttribute("allPosts", allPosts);

        return "homePage";
    }

    @GetMapping("about")
    public String getAboutPage() {
        return "about";
    }

    @GetMapping("archive")
    public String getArchivePage(Model model) {
        List<Post> allPosts = postService.getAllPosts();

        allPosts.sort((post, post2) -> post.getDateCreated().isAfter(post2.getDateCreated()) ? -1 : 1);

        model.addAttribute("allPosts", allPosts);

        return "archive";
    }

    @GetMapping("planets")
    public String getPlanetPage() {
        return "planets";
    }

    @GetMapping("readPost/{postId}")
    public String readPost(Model model, @PathVariable("postId") Long postId) {
        Post post = postService.findThePostById(postId);
        model.addAttribute("readPost", post);

        return "readPostPage";
    }
}
