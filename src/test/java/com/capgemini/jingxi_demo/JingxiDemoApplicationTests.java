package com.capgemini.jingxi_demo;

import com.capgemini.jingxi_demo.application.products.ProductService;
import com.capgemini.jingxi_demo.application.users.UserService;
import com.capgemini.jingxi_demo.infrastructure.entity.ProductEntity;
import com.capgemini.jingxi_demo.infrastructure.entity.UserEntity;
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
        UserEntity userEntity = new UserEntity();
        userEntity.setName("abc");
        System.out.println(userEntity);
        userService.SaveByUserName(userEntity);

        System.out.println("get user all");
        List<UserEntity> temp1 = userService.GetAll();
        for (int i = 0; i < temp1.size(); i++){
            UserEntity item = temp1.get(i);
            System.out.println(item.toString());
        }

        UserEntity userEntity3 = new UserEntity();
        userEntity3.setName("abc");
        userService.DeleteByUserName(userEntity3);

        System.out.println("get user all");
        List<UserEntity> temp2 = userService.GetAll();
        for (int i = 0; i < temp2.size(); i++){
            UserEntity item = temp2.get(i);
            System.out.println(item.toString());
        }

        System.out.println("============================");
        ProductEntity product = new ProductEntity();
        product.setName("book");
        product.setDescription("aboutspringboot");
        product.setQuantity(111);
        product.setPrice(BigDecimal.valueOf(2.44));
        System.out.println(product);
        productService.SaveProduct(product);

    }

}
