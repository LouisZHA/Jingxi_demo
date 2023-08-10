package com.capgemini.jingxi_demo.application.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductAddDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;

}
