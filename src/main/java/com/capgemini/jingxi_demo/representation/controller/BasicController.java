package com.capgemini.jingxi_demo.representation.controller;

import com.capgemini.jingxi_demo.infrastructure.entity.ProductEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class BasicController {

    // http://127.0.0.1:8080/html
    @RequestMapping("/html")
    public String html(){
        return "index.html";
    }

    // http://127.0.0.1:8080/hello?name=lisi
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(name = "name", defaultValue = "unknown user") String name) {
        return "Hello " + name;
    }

    // http://127.0.0.1:8080/product
    @RequestMapping("/product")
    @ResponseBody
    public ProductEntity productItem() {
        ProductEntity product = new ProductEntity();
        product.setName("book");
        product.setDescription("about s  pringboot");
        product.setQuantity(111);
        product.setPrice(BigDecimal.valueOf(2.44));
        return product;
    }

    // http://127.0.0.1:8080/productitem?name=newName&description=newdescription
    @RequestMapping("/productitem")
    @ResponseBody
    public ProductEntity saveProduct(ProductEntity product) {
        return product;
    }

    // http://127.0.0.1:8080/productitem?name=book&description=aboutspringboot&price=11&quantity=20
    @ModelAttribute
    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
            , @RequestParam(name = "description", defaultValue = "unknown description") String description
            , @RequestParam(name = "price", defaultValue = "111") BigDecimal price
            , @RequestParam(name = "quantity", defaultValue = "0") int quantity
            , ProductEntity p) {
        p.setName(name);
        p.setDescription(description);
        p.setPrice(price);
        p.setQuantity(quantity);
    }


}
