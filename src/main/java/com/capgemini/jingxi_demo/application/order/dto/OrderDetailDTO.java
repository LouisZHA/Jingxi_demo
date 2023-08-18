package com.capgemini.jingxi_demo.application.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {

    private Long id;
    private BigInteger order_id;
    private String product_name;
    private BigInteger product_id;
    private int quantity;
    private BigDecimal amount;
    private String image;

}
