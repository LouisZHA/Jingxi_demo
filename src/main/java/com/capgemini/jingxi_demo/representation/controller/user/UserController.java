package com.capgemini.jingxi_demo.representation.controller.user;

import com.capgemini.jingxi_demo.application.users.UserService;
import com.capgemini.jingxi_demo.application.users.dto.UserAddDTO;
import com.capgemini.jingxi_demo.application.users.dto.mapping.UserMapping;
import com.capgemini.jingxi_demo.infrastructure.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    // 添加用户
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@RequestBody UserAddDTO userAddDTO){
        userService.saveByUserName(userAddDTO);
        return "succssful add a product" + userAddDTO;
    }


}
