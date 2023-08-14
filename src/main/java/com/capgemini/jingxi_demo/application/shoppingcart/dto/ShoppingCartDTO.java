package com.capgemini.jingxi_demo.application.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDTO {
    private Long id;
    private String productid;
    private Integer quantity;
    private String productname;
    private BigDecimal amount;
    private String userid;
    private String image;
    private LocalDateTime updatedate;

}
