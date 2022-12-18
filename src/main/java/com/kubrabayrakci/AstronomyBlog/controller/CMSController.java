package com.kubrabayrakci.AstronomyBlog.controller;

import com.kubrabayrakci.AstronomyBlog.model.Post;
import com.kubrabayrakci.AstronomyBlog.model.Product;
import com.kubrabayrakci.AstronomyBlog.service.PostService;
import com.kubrabayrakci.AstronomyBlog.service.ProductService;
import com.kubrabayrakci.AstronomyBlog.util.ImageUtility;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String getCMSPage() {
        return "cmsHome";
    }

    @GetMapping("/createPost")
    public String getCreatePostPage(Model model){

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


    @PostMapping("/createPost")
    public String createNewPost(@RequestParam(name = "image1", required = false) MultipartFile multipartFile,
                                @ModelAttribute("newPost") Post post) throws IOException {
        if (multipartFile.getBytes().length > 0) {
            byte[] bytes = ImageUtility.compressImage(multipartFile.getBytes());
            post.setImage(ArrayUtils.toObject(bytes));
        }

        postService.savePost(post);

        return "redirect:/cms/showAllPost";
    }

    @PostMapping("/editPost")
    public String editPost(@RequestParam(name = "image1", required = false) MultipartFile multipartFile,
                           @RequestParam(name = "removeImage", required = false, defaultValue = "false") boolean removeImage,
                           @ModelAttribute("editedPost") Post post) throws IOException {

        if (multipartFile.getBytes().length > 0) {
            byte[] bytes = ImageUtility.compressImage(multipartFile.getBytes());
            post.setImage(ArrayUtils.toObject(bytes));
        } else if (removeImage) {
            post.setImage(null);
        } else {
            Post original = postService.findThePostById(post.getId());
            post.setImage(original.getImage());
        }

        postService.savePost(post);

        return "redirect:/cms/showAllPost";
    }

    @GetMapping("/duplicatePost/{id}")
    public String duplicateThePost(@PathVariable("id") Long postId) {
        Post post = postService.findThePostById(postId);
        Post duplicate = new Post();
        duplicate.setImage(post.getImage());
        duplicate.setDescription(post.getDescription());
        duplicate.setBody(post.getBody());
        duplicate.setTitle(post.getTitle());

        postService.savePost(duplicate);
        return "redirect:/cms/showAllPost";

    }

    @PostMapping("/editProduct")
    public String editProduct(@RequestParam(name = "image1", required = false) MultipartFile multipartFile,
                           @RequestParam(name = "removeImage", required = false, defaultValue = "false") boolean removeImage,
                           @ModelAttribute("editedProduct") Product product) throws IOException {

        if (multipartFile.getBytes().length > 0) {
            byte[] bytes = ImageUtility.compressImage(multipartFile.getBytes());
            product.setImage(ArrayUtils.toObject(bytes));
        } else if (removeImage) {
            product.setImage(null);
        } else {
            Product original = productService.findTheProductById(product.getId());
            product.setImage(original.getImage());
        }

        productService.saveProduct(product);

        return "redirect:/cms/showProducts";
    }


    @PostMapping("/createProduct")
    public String createNewProduct(@RequestParam(name = "image1", required = false) MultipartFile multipartFile,
                                @ModelAttribute("newProduct") Product product) throws IOException {

        if (multipartFile.getBytes().length > 0) {
            byte[] bytes = ImageUtility.compressImage(multipartFile.getBytes());
            product.setImage(ArrayUtils.toObject(bytes));
        }

        productService.saveProduct(product);

        return "redirect:/cms/showProducts";
    }

    @GetMapping("/showAllPost")
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

    @PostMapping("/deletePost/{id}")
    public String deleteThePost(@PathVariable("id") Long postId){

        postService.deletePost(postId);
        return "redirect:/cms/showAllPost";

    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteTheProduct(@PathVariable("id") Long productId){

        productService.deleteProduct(productId);
        return "redirect:/cms/showProducts";

    }

    @GetMapping("/duplicateProduct/{id}")
    public String duplicateTheProduct(@PathVariable("id") Long productId) {
        Product product = productService.findTheProductById(productId);
        Product duplicate = new Product();
        duplicate.setImage(product.getImage());
        duplicate.setDescription(product.getDescription());
        duplicate.setName(product.getName());
        duplicate.setPrice(product.getPrice());

        productService.saveProduct(duplicate);
        return "redirect:/cms/showProducts";

    }


    @GetMapping("/editPost/{id}")
    public String editThePost(@PathVariable("id") Long postId, Model model){

        Post post = postService.findThePostById(postId);
        model.addAttribute("editedPost", post);

        return "editPost";

    }

    @GetMapping("/editProduct/{id}")
    public String editTheProduct(@PathVariable("id") Long productId, Model model){

        Product product = productService.findTheProductById(productId);
        model.addAttribute("editedProduct", product);

        return "editProduct";

    }

}

