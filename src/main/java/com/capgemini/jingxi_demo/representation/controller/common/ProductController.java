package com.capgemini.jingxi_demo.representation.controller.common;

import com.capgemini.jingxi_demo.application.products.ProductService;
import com.capgemini.jingxi_demo.infrastructure.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    // 进行商品信息的模糊查询:
    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductEntity> addproduct(@RequestParam String query){
        return productService.getByKeyword(query);
    }

}
