package com.capgemini.jingxi_demo.representation.controller;

import com.capgemini.jingxi_demo.application.service.ProductService;
import com.capgemini.jingxi_demo.infrastructure.entity.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // http://127.0.0.1:8080/product/addproduct

    @RequestMapping(value = "addproduct", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String addproduct(@RequestBody ProductItem productItem){
        System.out.println("++++++++++++++++++++");
        System.out.println(productItem);
        productService.SaveProduct(productItem);
        return "succssful add a product" + productItem;
    }







}
