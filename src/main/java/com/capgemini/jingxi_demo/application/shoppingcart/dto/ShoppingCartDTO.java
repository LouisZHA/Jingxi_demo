package com.capgemini.jingxi_demo.application.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDTO {

    private Long id;
    private BigInteger product_id;
    private Integer quantity;
    private String product_name;
    private BigDecimal amount;
    private BigInteger user_id;
    private String image;
    private Timestamp update_date;
    private BigDecimal price;

}
