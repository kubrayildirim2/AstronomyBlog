package com.kubrabayrakci.AstronomyBlog.controller;

import com.kubrabayrakci.AstronomyBlog.model.Post;
import com.kubrabayrakci.AstronomyBlog.service.PostService;
import com.kubrabayrakci.AstronomyBlog.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public String createNewPost(@RequestParam(name = "image1", required = false)MultipartFile multipartFile,
                                @ModelAttribute("newPost") Post post) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        post.setImage(fileName);
        postService.savePost(post);
        String uploadDir = "post-image/" + post.getId();
        FileUploadUtil.saveFile(uploadDir,fileName,multipartFile);

        return "redirect:/cms";
    }

    @GetMapping("/showpost")
    public String showThePosts(Model model){

        List<Post> allPosts = postService.getAllPosts();
        model.addAttribute("allPosts", allPosts);

        return "posts";

    }
    @GetMapping("/delete/{id}")
    public String deleteThePost(@PathVariable("id") Long postId){

        postService.deletePost(postId);
        return "redirect:/cms/showpost";

    }
    @GetMapping("/edit/{id}")
    public String editThePost(@PathVariable("id") Long postId, Model model){

        Post post = postService.findThePostById(postId);
        model.addAttribute("newPost", post);

        return "createPost";

    }

}
