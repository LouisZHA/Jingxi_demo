package com.capgemini.jingxi_demo.application.order.dto.mapping;

import com.capgemini.jingxi_demo.application.order.dto.OrderAddDTO;
import com.capgemini.jingxi_demo.infrastructure.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapping {

    OrderEntity toOrderEntity(OrderAddDTO orderAddDTO);

    OrderAddDTO toOrderAddDTO(OrderEntity orderEntity);

}
