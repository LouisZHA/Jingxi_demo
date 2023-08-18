package com.capgemini.jingxi_demo.infrastructure.repository;

import com.capgemini.jingxi_demo.infrastructure.entity.DeliveryEntity;
import com.capgemini.jingxi_demo.infrastructure.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {

    Optional<DeliveryEntity> findById(Long aLong);

    // 根据order_id查看对应的快递单
    @Query(value = "select * from delivery where order_id = :order_id", nativeQuery = true)
    Optional<DeliveryEntity> findByOrder_id(@Param("order_id") BigInteger order_id);

    @Transactional
    @Modifying
    @Query(value="update delivery set delivery_status = :delivery_status where order_id = :order_id", nativeQuery = true)
    void ModifyDelivery_statusById(@Param("delivery_status") Integer delivery_status, @Param("order_id") BigInteger order_id);

    @Transactional
    @Modifying
    @Query(value="update delivery set delivery_status = :delivery_status where id = :id", nativeQuery = true)
    void ModifyDelivery_statusBydeliveryId(@Param("delivery_status") Integer delivery_status, @Param("id") BigInteger id);


    @Transactional
    @Modifying
    @Query(value="update delivery set update_time = :update_time where order_id = :order_id", nativeQuery = true)
    void Modifyupdate_timeById(@Param("update_time") Timestamp update_time, @Param("order_id") BigInteger order_id);

    @Transactional
    @Modifying
    @Query(value="update delivery set order_id = :order_id where id = :id", nativeQuery = true)
    void ModifyOrderIdById(@Param("id") BigInteger delivery_id, @Param("order_id") BigInteger order_id);

}
