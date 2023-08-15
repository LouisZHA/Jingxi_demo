package com.capgemini.jingxi_demo.representation.controller.user;

import com.capgemini.jingxi_demo.application.shoppingcart.ShoppingCartService;
import com.capgemini.jingxi_demo.application.shoppingcart.dto.ShoppingCartAddDTO;
import com.capgemini.jingxi_demo.application.shoppingcart.dto.mapping.ShoppingCartMapping;
import com.capgemini.jingxi_demo.infrastructure.entity.ShoppingCartEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/shoppingcart")
@RequiredArgsConstructor
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartMapping shoppingCartMapping;

    // http://127.0.0.1:8080/user/shoppingcart/add

    @RequestMapping(value = "add")
    public String html(){
        return "this is http://127.0.0.1:8080/user/shoppingcart/add. Add the product to the shopping cart by sending a post";
    }

    // 根据商品id和数量来查询商品的剩余信息，在数量不超出库存的情况下将商品添加到用户的购物车。
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public String addproduct(@RequestBody ShoppingCartAddDTO shoppingCartAddDTO){
        System.out.println("#########################");
        System.out.println(shoppingCartAddDTO);
        ShoppingCartEntity shoppingCartEntity = shoppingCartMapping.toShoppingCartEntity(shoppingCartAddDTO);
        System.out.println(shoppingCartEntity);
        shoppingCartService.addtoShoppingCart(shoppingCartAddDTO);
        return "succssful add a product to the shopping cart" + shoppingCartEntity;
    }

    // 清空购物车（/empty）

    // 结算购物车（/）

}
