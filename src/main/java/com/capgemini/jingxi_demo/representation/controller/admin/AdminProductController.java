package com.capgemini.jingxi_demo.representation.controller.admin;

import com.capgemini.jingxi_demo.application.products.ProductService;
import com.capgemini.jingxi_demo.application.products.dto.ProductAddDTO;
import com.capgemini.jingxi_demo.application.products.dto.mapping.ProductMapping;
import com.capgemini.jingxi_demo.infrastructure.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapping productMapping;

    // http://127.0.0.1:8080/admin/product/add

    @RequestMapping(value = "add")
    public String html(){
        return "this is http://127.0.0.1:8080/admin/product/add. Add the product by sending a post";
    }

    // 添加商品
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public String addproduct(@RequestBody ProductAddDTO productAddDTO){
        productService.saveProduct(productAddDTO);
        return "succssful add a product" + productAddDTO;
    }

    // 修改商品信息

    // 删除商品

    // 订单完成时：减少库存信息的真实数量




}
