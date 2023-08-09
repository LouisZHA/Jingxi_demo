/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.capgemini.jingxi_demo.apis.controller;

import com.capgemini.jingxi_demo.domain.entity.ProductItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Controller
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
    public ProductItem productItem() {
        ProductItem product = new ProductItem();
        product.setName("book");
        product.setDescription("about springboot");
        product.setQuantity(111);
        product.setPrice(BigDecimal.valueOf(2.44));
        return product;
    }

    // http://127.0.0.1:8080/productitem?name=newName&description=newdescription
    @RequestMapping("/productitem")
    @ResponseBody
    public ProductItem saveProduct(ProductItem product) {
        // return "product will save: name=" + product.getName() + " description=" + product.getDescription() + "  price=" + product.getPrice() + "  quantity=" + product.getQuantity();
//        return product.getPrice().toString();
        return product;
    }

    // http://127.0.0.1:8080/productitem?name=book&description=aboutspringboot&price=11&quantity=20
    @ModelAttribute
    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
            , @RequestParam(name = "description", defaultValue = "unknown description") String description
            , @RequestParam(name = "price", defaultValue = "0") BigDecimal price
            , @RequestParam(name = "quantity", defaultValue = "0") int quantity
            , ProductItem p) {
        p.setName(name);
        p.setDescription(description);
        p.setPrice(price);
        p.setQuantity(quantity);
    }


}
