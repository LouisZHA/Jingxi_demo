package com.capgemini.jingxi_demo.infrastructure.repository;

import com.capgemini.jingxi_demo.infrastructure.entity.ProductEntity;
import com.capgemini.jingxi_demo.infrastructure.entity.ShoppingCartEntity;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, String> {

    // 根据用户ID查看购物车全部商品
    @Query(value = "select * from shoppingcart where user_id = :id", nativeQuery = true)
    List<ShoppingCartEntity> GetShoppingCartByUserId(@Param("id") long id);

    // 根据用户ID清空购物车全部商品
    @Transactional
    @Modifying
    @Query(value = "delete from shoppingcart where user_id = :id",nativeQuery = true)
    void deleteByUserId(@Param("id") long id);

}
