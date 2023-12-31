package com.capgemini.jingxi_demo.infrastructure.repository;

import com.capgemini.jingxi_demo.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
    @Query(value = "SELECT * FROM productitem", nativeQuery = true)
    List<ProductEntity> GetAllProductData();

    @Query(value = "select * from productitem where name = :name", nativeQuery = true)
    List<ProductEntity> GetProductByName(@Param("name") String name);

    List<ProductEntity> findByNameContaining(String name);
    List<ProductEntity> findByDescriptionContaining(String name);
    Optional<ProductEntity> findByName(String name);

    @Transactional
    @Modifying
    @Query(value="update productitem set price = :price where id = :id", nativeQuery = true)
    void ModifyProductPriceById(@Param("id") Long id, @Param("price") BigDecimal price);

    @Transactional
    @Modifying
    @Query(value="update productitem set description = :description where id = :id", nativeQuery = true)
    void ModifyProductDescriptionById(@Param("id") Long id, @Param("description") String description);

    @Transactional
    @Modifying
    @Query(value="update productitem set name = :name where id = :id", nativeQuery = true)
    void ModifyProductNameById(@Param("id") Long id, @Param("name") String name);

    @Transactional
    @Modifying
    @Query(value="update productitem set image = :image where id = :id", nativeQuery = true)
    void ModifyProductImageById(@Param("id") Long id, @Param("image") String image);

    @Transactional
    @Modifying
    @Query(value="update productitem set quantity = :quantity where id = :id", nativeQuery = true)
    void ModifyProductQuantityById(@Param("id") Long id, @Param("quantity") int quantity);

    @Transactional
    @Modifying
    @Query(value="update productitem set quantity_locked = :quantity_locked where id = :id", nativeQuery = true)
    void ModifyProductQuantity_lockedById(@Param("id") Long id, @Param("quantity_locked") int quantity_locked);

}
