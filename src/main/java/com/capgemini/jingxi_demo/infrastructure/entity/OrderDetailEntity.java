package com.capgemini.jingxi_demo.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Table(name = "orderdetail")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Entity
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "order_id", unique = true)
    private BigInteger order_id;

    @Column(name = "product_name")
    private String product_name;

    @Column(name = "product_id", unique = true)
    private BigInteger product_id;

    @Column(name = "quantity", unique = true)
    private int quantity;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "image")
    private String image;

}
