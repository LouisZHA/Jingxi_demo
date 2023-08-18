package com.capgemini.jingxi_demo.infrastructure.entity;

import com.capgemini.jingxi_demo.common.enums.PayStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@Table(name = "orderitem")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "user_id", unique = true)
    private BigInteger user_id;

    @Column(name = "delivery_id")
    private BigInteger delivery_id;

    @CreatedDate
    @Column(name = "creat_time")
    private Timestamp creat_time;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "checkout_time")
    private Timestamp checkout_time;

    @Column(name = "cancel_time")
    private Timestamp cancel_time;

    @Column(name = "pay_status")
    private PayStatusEnum pay_status;

    @Column(name = "finish_time")
    private Timestamp finish_time;

}
