package com.capgemini.jingxi_demo.application.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductAddDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;

}
