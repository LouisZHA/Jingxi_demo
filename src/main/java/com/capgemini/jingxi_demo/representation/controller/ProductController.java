package com.capgemini.jingxi_demo.representation.controller;

import com.capgemini.jingxi_demo.application.products.ProductService;
import com.capgemini.jingxi_demo.application.products.dto.ProductAddDTO;
import com.capgemini.jingxi_demo.application.products.dto.mapping.ProductMapping;
import com.capgemini.jingxi_demo.infrastructure.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapping productMapping;


    // http://127.0.0.1:8080/product/addproduct

    @RequestMapping(value = "addproduct", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String addproduct(@RequestBody ProductAddDTO productAddDTO){
        System.out.println("++++++++++++++++++++");
        System.out.println(productAddDTO);
        ProductEntity productEntity = productMapping.toProductEntity(productAddDTO);
        productService.SaveProduct(productEntity);
        System.out.println("====================");
        System.out.println(productEntity);
        return "succssful add a product" + productEntity;
    }







}
