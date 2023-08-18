package com.capgemini.jingxi_demo.infrastructure.repository;

import com.capgemini.jingxi_demo.infrastructure.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {

    // 根据用户ID查看购物车全部商品
    @Query(value = "select * from shoppingcart where user_id = :id", nativeQuery = true)
    List<ShoppingCartEntity> GetShoppingCartByUserId(@Param("id") BigInteger id);

    // 根据用户ID和商品id查看购物车全部商品
    @Query(value = "select * from shoppingcart where user_id = :id and product_id = :product_id", nativeQuery = true)
    List<ShoppingCartEntity> GetShoppingCartByUserId(@Param("id") BigInteger id, @Param("product_id") BigInteger product_id);

    // 根据用户ID清空购物车全部商品
    @Transactional
    @Modifying
    @Query(value = "delete from shoppingcart where user_id = :id",nativeQuery = true)
    void deleteByUserId(@Param("id") BigInteger id);

    // 根据用户ID和商品ID,删除购物车中的一种商品
    @Transactional
    @Modifying
    @Query(value = "delete from shoppingcart where user_id = :id and product_id = :product_id",nativeQuery = true)
    void deleteByUserIdAndProductId(@Param("id") BigInteger id, @Param("product_id") BigInteger product_id);

    //当前加入到购物车中的商品是否已经存在在购物车了，调整商品数量。
    @Transactional
    @Modifying
    @Query(value = "update shoppingcart set quantity = :quantity, amount = :amount where id = :id",nativeQuery = true)
    void updateQuantityById(@Param("id") BigInteger id, @Param("quantity") Integer quantity, @Param("amount") BigDecimal amount);

}
