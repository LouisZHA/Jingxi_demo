package com.capgemini.jingxi_demo.representation.controller.user;

import com.capgemini.jingxi_demo.application.users.UserService;
import com.capgemini.jingxi_demo.application.users.dto.UserAddDTO;
import com.capgemini.jingxi_demo.application.users.dto.mapping.UserMapping;
import com.capgemini.jingxi_demo.infrastructure.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapping userMapping;

    // http://127.0.0.1:8080/user/add
    @RequestMapping(value = "add")
    public String html(){
        return "this is http://127.0.0.1:8080/user/add. Add the user by sending a post";
    }

    // 添加用户
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@RequestBody UserAddDTO userAddDTO){
        UserEntity userEntity = userMapping.toUserEntity(userAddDTO);
        userService.saveByUserName(userEntity);
        return "succssful add a product" + userEntity;
    }


}
