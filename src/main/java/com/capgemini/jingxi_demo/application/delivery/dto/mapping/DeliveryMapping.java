package com.capgemini.jingxi_demo.application.delivery.dto.mapping;

import com.capgemini.jingxi_demo.application.delivery.dto.DeliveryAddDTO;
import com.capgemini.jingxi_demo.infrastructure.entity.DeliveryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeliveryMapping {

    DeliveryEntity toDeliveryEntity(DeliveryAddDTO deliveryAddDTO);

    DeliveryAddDTO toDeliveryAddDTO(DeliveryEntity deliveryEntity);

}
