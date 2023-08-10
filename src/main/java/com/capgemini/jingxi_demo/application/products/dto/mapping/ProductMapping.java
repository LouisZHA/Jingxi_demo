package com.capgemini.jingxi_demo.application.products.dto.mapping;

import com.capgemini.jingxi_demo.application.products.dto.ProductAddDTO;
import com.capgemini.jingxi_demo.infrastructure.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapping {

//    @Mapping(source = "id", target = "idDTO")
//    @Mapping(source = "name", target = "nameDTO")
//    @Mapping(source = "description", target = "descriptionDTO")
//    @Mapping(source = "price", target = "idDTO")
//    @Mapping(source = "quantity", target = "realNameDTO")
    ProductEntity toProductEntity(ProductAddDTO productAddDTO);

    ProductAddDTO toProductAddDTO(ProductEntity productEntity);

}
