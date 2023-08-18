package com.capgemini.jingxi_demo.application.delivery.dto;

import com.capgemini.jingxi_demo.common.enums.DeliveryStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddDTO {

    private Long id;
    private BigInteger order_id;
    private DeliveryStatusEnum delivery_status;
    private Timestamp creat_time;
    private Timestamp update_time;

}
