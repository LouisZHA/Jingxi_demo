package com.capgemini.jingxi_demo.representation.controller.user;

import com.capgemini.jingxi_demo.application.order.OrderService;
import com.capgemini.jingxi_demo.application.shoppingcart.ShoppingCartService;
import com.capgemini.jingxi_demo.application.shoppingcart.dto.ShoppingCartAddDTO;
import com.capgemini.jingxi_demo.application.shoppingcart.dto.ShoppingCartDTO;
import com.capgemini.jingxi_demo.application.shoppingcart.dto.mapping.ShoppingCartMapping;
import com.capgemini.jingxi_demo.infrastructure.entity.ShoppingCartEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/shoppingcart")
@RequiredArgsConstructor
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private OrderService orderService;

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
        shoppingCartService.addtoShoppingCart(shoppingCartAddDTO);
        return "succssful add a product to the shopping cart" + shoppingCartAddDTO;
    }


    // 根据用户ID，查看用户的购物车（/show）
    @RequestMapping(value = "show", method = RequestMethod.POST)
    @ResponseBody
    public List<ShoppingCartEntity> showshoppingcart(@RequestBody ShoppingCartDTO shoppingCartDTO){
        List<ShoppingCartEntity> list = shoppingCartService.showShoppingCart(shoppingCartDTO);
        return list;
    }

    // 根据用户ID，结算用户的购物车（/checkout）
    @RequestMapping(value = "checkout", method = RequestMethod.POST)
    @ResponseBody
    public void checkoutshoppingcart(@RequestBody ShoppingCartDTO shoppingCartDTO){
        // todo 添加购物车内容到订单，并记录时间。

        // 清空购物车
        shoppingCartService.cleanShoppingCart(shoppingCartDTO);
    }

    // 根据用户ID，清空用户的购物车（/clean）
    @RequestMapping(value = "clean", method = RequestMethod.POST)
    @ResponseBody
    public void cleanshoppingcart(@RequestBody ShoppingCartDTO shoppingCartDTO){
        shoppingCartService.cleanShoppingCart(shoppingCartDTO);
    }

    // 根据用户ID和商品ID,删除购物车中的一种商品（/delete）
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public void deleteproduct(@RequestBody ShoppingCartDTO shoppingCartDTO){
        shoppingCartService.deleteProduct(shoppingCartDTO);
    }


}
