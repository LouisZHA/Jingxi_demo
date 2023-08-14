package com.capgemini.jingxi_demo.application.shoppingcart;

import com.capgemini.jingxi_demo.application.products.dto.mapping.ProductMapping;
import com.capgemini.jingxi_demo.application.shoppingcart.dto.ShoppingCartAddDTO;
import com.capgemini.jingxi_demo.application.shoppingcart.dto.ShoppingCartDTO;
import com.capgemini.jingxi_demo.application.shoppingcart.dto.mapping.ShoppingCartMapping;
import com.capgemini.jingxi_demo.infrastructure.entity.ProductEntity;
import com.capgemini.jingxi_demo.infrastructure.entity.ShoppingCartEntity;
import com.capgemini.jingxi_demo.infrastructure.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartMapping shoppingCartMapping;

    @Autowired
    private ProductMapping productMapping;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    // 根据用户ID查看全部购物车
    public List<ShoppingCartEntity> showShoppingCart(ShoppingCartDTO shoppingCartDTO){
        Long userId = shoppingCartDTO.getId();
        List<ShoppingCartEntity> list = shoppingCartRepository.GetShoppingCartByUserId(userId);
        return list;
    }

    // 清空购物车
    public void cleanShoppingCart(ShoppingCartDTO shoppingCartDTO){
        Long userId = shoppingCartDTO.getId();
        shoppingCartRepository.deleteByUserId(userId);
    }

    // 当添加商品时，输入商品的名称，描述，价格，系统应该创建商品，同时创建对应的库存数量为0。
//    public void addtoShoppingCart(ShoppingCartAddDTO shoppingCartAddDTO){
//        ShoppingCartEntity shoppingCartEntity = shoppingCartMapping.toShoppingCartEntity(shoppingCartAddDTO);
//        Long userId = shoppingCartEntity.getId();
//
//        shoppingCartRepository.save(shoppingCartEntity);
//    }

}
