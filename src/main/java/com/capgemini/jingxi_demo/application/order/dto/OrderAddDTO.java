package com.capgemini.jingxi_demo.application.order.dto;

import com.capgemini.jingxi_demo.common.enums.PayStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAddDTO {

    private Long id;
    private BigInteger user_id;
    private BigInteger delivery_id;
    private Timestamp creat_time;
    private BigDecimal amount;
    private Timestamp checkout_time;
    private Timestamp cancel_time;
    private PayStatusEnum pay_status;
    private Timestamp finish_time;

}
