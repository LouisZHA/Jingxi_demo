package com.capgemini.jingxi_demo.application.products.dto.mapping;

import com.capgemini.jingxi_demo.application.products.dto.ProductAddDTO;
import com.capgemini.jingxi_demo.infrastructure.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapping {

//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "name", target = "name")
//    @Mapping(source = "description", target = "description")
//    @Mapping(source = "price", target = "price")
//    @Mapping(source = "quantity", target = "quantity")
    ProductEntity toProductEntity(ProductAddDTO productAddDTO);

    ProductAddDTO toProductAddDTO(ProductEntity productEntity);

}
