package com.capgemini.jingxi_demo.application.products.dto.mapping;

import com.capgemini.jingxi_demo.application.products.dto.ProductAddDTO;
import com.capgemini.jingxi_demo.application.products.dto.ProductDTO;
import com.capgemini.jingxi_demo.infrastructure.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapping {

//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "name", target = "name")
//    @Mapping(source = "description", target = "description")
//    @Mapping(source = "price", target = "price")
//    @Mapping(source = "quantity", target = "quantity")
    ProductEntity toProductEntity(ProductAddDTO productAddDTO);

    ProductEntity toProductEntityFromDto(ProductDTO productDTO);

    ProductAddDTO toProductAddDTO(ProductEntity productEntity);

}
