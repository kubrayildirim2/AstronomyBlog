package com.kubrabayrakci.AstronomyBlog.controller;

import com.kubrabayrakci.AstronomyBlog.model.Product;
import com.kubrabayrakci.AstronomyBlog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public String getShopPage(Model model) {
        List<Product> productList = productService.getAllProducts();

        model.addAttribute("productList", productList);

        return "shopPage";
    }

    @PostMapping("/addToCart")
    public String addToCart(Model model) {

        return "redirect:/shopPage";
    }
}
