package com.capgemini.jingxi_demo.application.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDTO {
    private Long id;
    private BigInteger productid;
    private Integer quantity;
    private String productname;
    private BigDecimal amount;
    private BigInteger userid;
    private String image;
    private Timestamp updatedate;
    private BigDecimal price;

}
