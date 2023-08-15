package com.capgemini.jingxi_demo.application.shoppingcart;

import com.capgemini.jingxi_demo.application.products.ProductService;
import com.capgemini.jingxi_demo.application.products.dto.mapping.ProductMapping;
import com.capgemini.jingxi_demo.application.shoppingcart.dto.ShoppingCartAddDTO;
import com.capgemini.jingxi_demo.application.shoppingcart.dto.ShoppingCartDTO;
import com.capgemini.jingxi_demo.application.shoppingcart.dto.mapping.ShoppingCartMapping;
import com.capgemini.jingxi_demo.infrastructure.entity.ProductEntity;
import com.capgemini.jingxi_demo.infrastructure.entity.ShoppingCartEntity;
import com.capgemini.jingxi_demo.infrastructure.repository.ProductRepository;
import com.capgemini.jingxi_demo.infrastructure.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartMapping shoppingCartMapping;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    // 根据用户ID，返回全部购物车列表
    public List<ShoppingCartEntity> showShoppingCart(ShoppingCartDTO shoppingCartDTO){
        Long userId = shoppingCartDTO.getId();
        List<ShoppingCartEntity> list = shoppingCartRepository.GetShoppingCartByUserId(userId);
        return list;
    }

    // 清空该用户的购物车
    public void cleanShoppingCart(ShoppingCartDTO shoppingCartDTO){
        Long userId = shoppingCartDTO.getId();
        shoppingCartRepository.deleteByUserId(userId);
    }

    // 根据商品id和数量来查询商品的剩余信息，在数量不超出库存的情况下将商品添加到用户的购物车。
    public void addtoShoppingCart(ShoppingCartAddDTO shoppingCartAddDTO){
        ShoppingCartEntity shoppingCartEntity = shoppingCartMapping.toShoppingCartEntity(shoppingCartAddDTO);
        Long Product_Id = Long.valueOf(shoppingCartAddDTO.getProductid());
        Optional<ProductEntity> productEntity = productRepository.findById(Product_Id);
        if (productEntity.get().getQuantity() >= shoppingCartAddDTO.getQuantity()){
            shoppingCartRepository.save(shoppingCartEntity);
            productRepository.ModifyProductQuantityById(Product_Id.intValue(), productEntity.get().getQuantity()-shoppingCartAddDTO.getQuantity());
            System.out.println("add Product to the shopping cart successfully. and Modify the Product Quantity");
        }

    }

}
