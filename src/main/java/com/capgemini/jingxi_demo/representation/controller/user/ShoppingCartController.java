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

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/user/shoppingcart")
@RequiredArgsConstructor
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartMapping shoppingCartMapping;

    // 根据商品id和数量来查询商品的剩余信息，在数量不超出库存的情况下将商品添加到用户的购物车。(/add)
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public String addproduct(@RequestBody ShoppingCartAddDTO shoppingCartAddDTO){
        shoppingCartService.addtoShoppingCart(shoppingCartAddDTO);
        return "succssful add a product to the shopping cart" + shoppingCartAddDTO;
    }


    // 根据用户ID，查看用户的购物车（/show）
    @RequestMapping(value = "show", method = RequestMethod.GET)
    @ResponseBody
    public List<ShoppingCartEntity> showshoppingcart(@RequestParam BigInteger id){
        return shoppingCartService.showShoppingCart(id);
    }

    // 根据用户ID，清空用户的购物车（/clean）
    @RequestMapping(value = "clean", method = RequestMethod.PUT)
    @ResponseBody
    public void cleanshoppingcart(@RequestParam BigInteger userid){
        shoppingCartService.cleanShoppingCart(userid);
    }

    // 根据用户ID和商品ID,删除购物车中的一种商品（/delete）
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteproduct(@RequestParam BigInteger userid, @RequestParam BigInteger productid){
        shoppingCartService.deleteProduct(userid, productid);
    }


}
