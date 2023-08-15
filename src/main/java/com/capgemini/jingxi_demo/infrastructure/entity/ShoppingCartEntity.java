package com.capgemini.jingxi_demo.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Table(name = "shoppingcart")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShoppingCartEntity {

    // 表的内容： 自增id ；用户id；产品id（bigint）；商品名称；商品照片路径（varchar（255））； 产品数量（int）；产品价格（money）；创建时间（datetime）

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "product_id")
    private String productid;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "product_name")
    private String productname;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "user_id")
    private String userid;

    @Column(name = "image")
    private String image;

    @Column(name = "update_date")
    private LocalDateTime updatedate;

}
