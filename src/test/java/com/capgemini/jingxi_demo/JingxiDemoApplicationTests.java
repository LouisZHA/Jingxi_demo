package com.capgemini.jingxi_demo;

import com.capgemini.jingxi_demo.application.service.ProductService;
import com.capgemini.jingxi_demo.application.service.UserService;
import com.capgemini.jingxi_demo.infrastructure.entity.ProductItem;
import com.capgemini.jingxi_demo.infrastructure.entity.UserItem;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class JingxiDemoApplicationTests {

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    @Test
    void contextLoads() {

        System.out.println("============================");
        System.out.println("save user by name");
        UserItem userItem = new UserItem();
        userItem.setName("abc");
        System.out.println(userItem);
        userService.SaveByUserName(userItem);

        System.out.println("get user all");
        List<UserItem> temp1 = userService.GetAll();
        for (int i = 0; i < temp1.size(); i++){
            UserItem item = temp1.get(i);
            System.out.println(item.toString());
        }

        UserItem userItem3 = new UserItem();
        userItem3.setName("abc");
        userService.DeleteByUserName(userItem3);

        System.out.println("get user all");
        List<UserItem> temp2 = userService.GetAll();
        for (int i = 0; i < temp2.size(); i++){
            UserItem item = temp2.get(i);
            System.out.println(item.toString());
        }

        System.out.println("============================");
        ProductItem product = new ProductItem();
        product.setName("book");
        product.setDescription("aboutspringboot");
        product.setQuantity(111);
        product.setPrice(BigDecimal.valueOf(2.44));
        System.out.println(product);
        productService.SaveProduct(product);

    }

}
