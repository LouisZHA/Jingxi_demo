package com.capgemini.jingxi_demo.representation.controller.admin;

import com.capgemini.jingxi_demo.application.delivery.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/admin/delivery")
@RequiredArgsConstructor
public class AdminDeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    // 修改快递单状态 ： 权限1，2 (/status)
    @RequestMapping(value = "status", method = RequestMethod.PUT)
    @ResponseBody
    public void updateDeliveryStatus(@RequestParam BigInteger delivery_id, @RequestParam Integer delivery_status){
        if (delivery_status.equals(1) | delivery_status.equals(2)){
            deliveryService.updateDeliveryStatus(delivery_id, delivery_status);
        }
    }

}
