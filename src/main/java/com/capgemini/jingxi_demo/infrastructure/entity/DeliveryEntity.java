package com.capgemini.jingxi_demo.infrastructure.entity;

import com.capgemini.jingxi_demo.common.enums.DeliveryStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@Table(name = "delivery")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Entity
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "order_id", unique = true)
    private BigInteger order_id;

    @Column(name = "delivery_status")
    private DeliveryStatusEnum delivery_status;

    @CreatedDate
    @Column(name = "creat_time")
    private Timestamp creat_time;

    @LastModifiedDate
    @Column(name = "update_time")
    private Timestamp update_time;

}
