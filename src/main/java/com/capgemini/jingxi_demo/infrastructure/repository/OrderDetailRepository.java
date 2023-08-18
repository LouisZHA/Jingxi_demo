package com.capgemini.jingxi_demo.infrastructure.repository;

import com.capgemini.jingxi_demo.infrastructure.entity.DeliveryEntity;
import com.capgemini.jingxi_demo.infrastructure.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {

    // 根据order_id查看对应的订单详情页
    @Query(value = "select * from orderdetail where order_id = :order_id", nativeQuery = true)
    List<OrderDetailEntity> findByOrder_id(@Param("order_id") BigInteger order_id);
}
