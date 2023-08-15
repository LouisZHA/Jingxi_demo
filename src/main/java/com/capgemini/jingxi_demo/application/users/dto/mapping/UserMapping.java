package com.capgemini.jingxi_demo.application.users.dto.mapping;

import com.capgemini.jingxi_demo.application.users.dto.UserAddDTO;
import com.capgemini.jingxi_demo.infrastructure.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapping {

//    @Mapping(source = "name", target = "name")
    UserEntity toUserEntity(UserAddDTO userAddDTO);

    UserAddDTO toUserAddDTO(UserEntity userEntity);

}
