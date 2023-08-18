package com.capgemini.jingxi_demo.application.users;

import com.capgemini.jingxi_demo.application.users.dto.UserAddDTO;
import com.capgemini.jingxi_demo.application.users.dto.mapping.UserMapping;
import com.capgemini.jingxi_demo.infrastructure.entity.ProductEntity;
import com.capgemini.jingxi_demo.infrastructure.entity.UserEntity;
import com.capgemini.jingxi_demo.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapping userMapping;

    // 增
    public void saveByUserName(UserAddDTO userAddDTO){
        UserEntity userEntity = userMapping.toUserEntity(userAddDTO);
        Optional<UserEntity> p = userRepository.findByName(userEntity.getName());
        if (p.isEmpty()){
            userRepository.save(userEntity);
        }else {
            System.out.println("用户名已存在");
        }
    }

    // 删
    public void deleteByUserName(UserAddDTO userAddDTO){
        UserEntity userEntity = userMapping.toUserEntity(userAddDTO);
        Optional<UserEntity> p = userRepository.findByName(userEntity.getName());
        if (!p.isEmpty()){
            userRepository.delete(userEntity);
        }else {
            System.out.println("用户名不存在");
        }
    }

    // 改
    public void modifyName(String name, int id){
        userRepository.ModifyUserByidWithName(name, id);
    }

    // 查
    public List<UserEntity> getAll(){
        return userRepository.GetAllUserData();
    }

    public List<UserEntity> getByUserName(String name){
        return userRepository.GetUserByName(name);
    }


}
