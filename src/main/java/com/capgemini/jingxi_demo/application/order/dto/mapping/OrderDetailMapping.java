package com.capgemini.jingxi_demo.application.order.dto.mapping;

import com.capgemini.jingxi_demo.application.order.dto.OrderDetailDTO;
import com.capgemini.jingxi_demo.infrastructure.entity.OrderDetailEntity;
import com.capgemini.jingxi_demo.infrastructure.entity.ShoppingCartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderDetailMapping {
    OrderDetailEntity toOrderDetailEntity(OrderDetailDTO orderDetailDTO);

    List<OrderDetailEntity> toOrderDetailEntitylist(List<ShoppingCartEntity> shoppingCartEntitys);

    OrderDetailDTO toOrderDetailDTO(OrderDetailEntity orderDetailEntity);
}
