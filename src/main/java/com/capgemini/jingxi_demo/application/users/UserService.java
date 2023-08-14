package com.capgemini.jingxi_demo.application.users;

import com.capgemini.jingxi_demo.infrastructure.entity.UserEntity;
import com.capgemini.jingxi_demo.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 增
    public void saveByUserName(UserEntity u){
        userRepository.save(u);
    }

    // 删
    public void deleteByUserName(UserEntity u){
        userRepository.delete(u);
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
