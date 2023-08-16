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

import java.math.BigDecimal;
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
        BigInteger userId = shoppingCartDTO.getUserid();
        List<ShoppingCartEntity> list = shoppingCartRepository.GetShoppingCartByUserId(userId);
        return list;
    }

    // 根据用户ID，清空用户的购物车
    public void cleanShoppingCart(ShoppingCartDTO shoppingCartDTO){
        BigInteger userId = shoppingCartDTO.getUserid();
        shoppingCartRepository.deleteByUserId(userId);
        System.out.println("根据用户ID，清空用户的购物车");
    }

    // 根据用户ID和商品ID,删除购物车中的一种商品
    public void deleteProduct(ShoppingCartDTO shoppingCartDTO){
        BigInteger userid = shoppingCartDTO.getUserid();
        BigInteger productid = shoppingCartDTO.getProductid();
        shoppingCartRepository.deleteByUserIdAndProductId(userid, productid);
        System.out.println("根据用户ID和商品ID,删除购物车中的一种商品");
    }

    // 根据商品id和数量来查询商品的剩余信息，在数量不超出库存的情况下将商品添加到用户的购物车。
    public void addtoShoppingCart(ShoppingCartAddDTO shoppingCartAddDTO){
        ShoppingCartEntity shoppingCartEntity = shoppingCartMapping.toShoppingCartEntity(shoppingCartAddDTO);
        BigInteger Product_id = shoppingCartAddDTO.getProductid();
        BigInteger User_id = shoppingCartAddDTO.getUserid();
        Optional<ProductEntity> productEntity = productRepository.findById(Product_id.longValue());
        List<ShoppingCartEntity> list = shoppingCartRepository.GetShoppingCartByUserId(User_id, Product_id);

        if (productEntity.isPresent()){
            if(list != null && list.size() > 0){
                // 当前加入到购物车中的商品是否已经存在在购物车了，需要调整商品数量。
                ShoppingCartEntity cart = list.get(0);
                if (productEntity.get().getQuantity() >= shoppingCartAddDTO.getQuantity()){
                    cart.setQuantity(cart.getQuantity()+shoppingCartAddDTO.getQuantity());
                    cart.setAmount(cart.getPrice().multiply(new BigDecimal(cart.getQuantity())));
                    shoppingCartRepository.updateQuantityById(BigInteger.valueOf(cart.getId()), cart.getQuantity(), cart.getAmount());
                    productRepository.ModifyProductQuantityById(Product_id.intValue(), productEntity.get().getQuantity()-shoppingCartAddDTO.getQuantity());
                    System.out.println("前加入到购物车中的商品是否已经存在,成功修改了购物车商品数量，并修改了商品库存数据");
                }else {
                    System.out.println("库存不足");
                }
            }else {
                if (productEntity.get().getQuantity() >= shoppingCartAddDTO.getQuantity()){
                    shoppingCartEntity.setProductname(productEntity.get().getName());
                    shoppingCartEntity.setPrice(productEntity.get().getPrice());
                    shoppingCartEntity.setAmount(productEntity.get().getPrice().multiply(new BigDecimal(shoppingCartEntity.getQuantity())));
                    shoppingCartEntity.setImage(productEntity.get().getImage());
                    shoppingCartRepository.save(shoppingCartEntity);
                    productRepository.ModifyProductQuantityById(Product_id.intValue(), productEntity.get().getQuantity()-shoppingCartAddDTO.getQuantity());
                    System.out.println("成功添加到购物车，并修改了商品库存数据");
                }else {
                    System.out.println("库存不足");
                }
            }
        }else {
            System.out.println("商品id不存在");
        }
    }

}
