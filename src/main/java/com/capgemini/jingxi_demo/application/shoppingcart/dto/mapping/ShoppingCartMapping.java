package com.capgemini.jingxi_demo.application.shoppingcart.dto.mapping;

import com.capgemini.jingxi_demo.application.shoppingcart.dto.ShoppingCartAddDTO;
import com.capgemini.jingxi_demo.infrastructure.entity.ShoppingCartEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShoppingCartMapping {
    ShoppingCartEntity toShoppingCartEntity(ShoppingCartAddDTO shoppingCartAddDTO);

    ShoppingCartAddDTO toShoppingCartAddDTO(ShoppingCartEntity shoppingCartEntity);


}
