package com.capgemini.jingxi_demo.representation.controller.user;

import com.capgemini.jingxi_demo.application.products.ProductService;
import com.capgemini.jingxi_demo.application.products.dto.ProductAddDTO;
import com.capgemini.jingxi_demo.application.products.dto.mapping.ProductMapping;
import com.capgemini.jingxi_demo.infrastructure.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/product")
public class UserProductController {

    @Autowired
    private ProductService productService;

    // http://127.0.0.1:8080/user/product/add

    @RequestMapping(value = "add")
    public String html(){
        return "This is http://127.0.0.1:8080/user/product/add. Add the product by sending a post";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public String addproduct(@RequestBody ProductAddDTO productAddDTO){
        productService.saveProduct(productAddDTO);
        return "succssful add a product" + productAddDTO;
    }

    // 订单完成时：减少库存信息的真实数量







}
