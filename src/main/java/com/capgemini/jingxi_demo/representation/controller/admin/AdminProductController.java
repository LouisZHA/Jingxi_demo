package com.capgemini.jingxi_demo.representation.controller.admin;

import com.capgemini.jingxi_demo.application.products.ProductService;
import com.capgemini.jingxi_demo.application.products.dto.ProductAddDTO;
import com.capgemini.jingxi_demo.application.products.dto.ProductDTO;
import com.capgemini.jingxi_demo.infrastructure.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class AdminProductController {

    @Autowired
    private ProductService productService;

    // 添加商品:
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public String addproduct(@RequestBody ProductAddDTO productAddDTO){
        productService.saveProduct(productAddDTO);
        return "succssful add a product" + productAddDTO;
    }

    // 修改商品信息：
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody ProductDTO productDTO){
        productService.ModifyProduct(productDTO);
    }

    // 删除商品
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@RequestParam Long id){
        productService.deleteById(id);
    }

    // 修改锁定库存数量
    @RequestMapping(value = "quantity_locked", method = RequestMethod.PUT)
    @ResponseBody
    public void updateQuantity_locked(@RequestParam Long id, @RequestParam Integer Quantity_locked){
        productService.ModifyQuantity_locked(id, Quantity_locked);
    }

    // 修改商品库存数量
    @RequestMapping(value = "quantity", method = RequestMethod.PUT)
    @ResponseBody
    public void updateQuantity(@RequestParam Long id, @RequestParam Integer Quantity){
        productService.ModifyQuantity(id, Quantity);
    }


}
