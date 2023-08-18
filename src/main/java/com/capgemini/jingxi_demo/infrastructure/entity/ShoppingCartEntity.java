package com.capgemini.jingxi_demo.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@Table(name = "shoppingcart")
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@Entity
public class ShoppingCartEntity {

    // 表的内容： 自增id ；用户id；产品id（bigint）；商品名称；商品照片路径（varchar（255））； 产品数量（int）；产品价格（money）；创建时间（datetime）

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "product_id", unique = true)
    private BigInteger product_id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "product_name")
    private String product_name;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "user_id")
    private BigInteger user_id;

    @Column(name = "image")
    private String image;

    // @Column(name="CREATE_TIME",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP",insertable = false,updatable = false)
    @LastModifiedDate
    @Column(name = "update_date")
    private Timestamp update_date;

    @Column(name = "price")
    private BigDecimal price;

}
