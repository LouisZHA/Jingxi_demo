package com.capgemini.jingxi_demo.infrastructure.repository;

import com.capgemini.jingxi_demo.infrastructure.entity.DeliveryEntity;
import com.capgemini.jingxi_demo.infrastructure.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    // 根据userid查看对应的订单表
    @Query(value = "select * from orderitem where user_id = :user_id", nativeQuery = true)
    List<OrderEntity> findByUser_id(@Param("user_id") BigInteger user_id);

    @Transactional
    @Modifying
    @Query(value="update orderitem set pay_status = :pay_status where id = :id", nativeQuery = true)
    void ModifyPay_statusById(@Param("pay_status") Integer pay_status, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value="update orderitem set finish_time = :finish_time where id = :id", nativeQuery = true)
    void Modifyfinish_timeById(@Param("finish_time") Timestamp finish_time, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value="update orderitem set cancel_time = :cancel_time where id = :id", nativeQuery = true)
    void Modifycancel_timeById(@Param("cancel_time") Timestamp cancel_time, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value="update orderitem set checkout_time = :checkout_time where id = :id", nativeQuery = true)
    void Modifycheckout_timeById(@Param("checkout_time") Timestamp checkout_time, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value="update orderitem set delivery_id = :delivery_id where id = :id", nativeQuery = true)
    void ModifydeliveryIdById(@Param("delivery_id") BigInteger delivery_id, @Param("id") BigInteger id);

}
