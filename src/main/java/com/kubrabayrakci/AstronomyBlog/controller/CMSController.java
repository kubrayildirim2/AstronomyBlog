package com.kubrabayrakci.AstronomyBlog.controller;

import com.kubrabayrakci.AstronomyBlog.model.Post;
import com.kubrabayrakci.AstronomyBlog.model.Product;
import com.kubrabayrakci.AstronomyBlog.service.PostService;
import com.kubrabayrakci.AstronomyBlog.service.ProductService;
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

    @Autowired
    ProductService productService;

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

    @GetMapping("/createProduct")
    public String getCreateProductPage(Model model){

        Product product = new Product();
        model.addAttribute("newProduct", product);

        return "createProduct";
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

    @PostMapping("/createProduct")
    public String createNewProduct(@RequestParam(name = "image1", required = false)MultipartFile multipartFile,
                                @ModelAttribute("newProduct") Product product) throws IOException {
        //String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
       // product.setImage(fileName);
        productService.saveProduct(product);

       // String uploadDir = "post-image/" + product.getId();
        // FileUploadUtil.saveFile(uploadDir,fileName,multipartFile);

        return "redirect:/cms/showProducts";
    }

    @GetMapping("/showpost")
    public String showThePosts(Model model){

        List<Post> allPosts = postService.getAllPosts();
        model.addAttribute("allPosts", allPosts);

        return "posts";

    }

    @GetMapping("/showProducts")
    public String showTheProducts(Model model) {

        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("allProducts", allProducts);

        return "products";

    }

    @GetMapping("/delete/{id}")
    public String deleteThePost(@PathVariable("id") Long postId){

        postService.deletePost(postId);
        return "redirect:/cms/showpost";

    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteTheProduct(@PathVariable("id") Long productId){

        productService.deleteProduct(productId);
        return "redirect:/cms/showProducts";

    }
    @GetMapping("/edit/{id}")
    public String editThePost(@PathVariable("id") Long postId, Model model){

        Post post = postService.findThePostById(postId);
        model.addAttribute("newPost", post);

        return "createPost";

    }

    @GetMapping("/editProduct/{id}")
    public String editTheProduct(@PathVariable("id") Long productId, Model model){

        Product product = productService.findTheProductById(productId);
        model.addAttribute("newProduct", product);
        model.addAttribute("isEdit", true);

        return "createProduct";

    }

}

