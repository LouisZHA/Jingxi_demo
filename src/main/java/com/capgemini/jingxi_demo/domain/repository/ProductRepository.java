package com.capgemini.jingxi_demo.domain.repository;

import com.capgemini.jingxi_demo.domain.entity.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductItem, String>{
    @Query(value = "SELECT * FROM productitem", nativeQuery = true)
    List<ProductItem> GetAllProductData();

    @Query(value = "select * from productitem where name = :name", nativeQuery = true)
    List<ProductItem> GetProductByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value="update productitem set count = :count where name = :name", nativeQuery = true)
    void ModifyProductByNameWithCount(@Param("name") String name, @Param("count") int count);

    @Transactional
    @Modifying
    @Query(value="update productitem set price = :price where name = :name", nativeQuery = true)
    void ModifyProductByNameWithPrice(@Param("name") String name, @Param("price") BigDecimal price);

}
