package com.capgemini.jingxi_demo.representation.controller.courier;

import com.capgemini.jingxi_demo.application.delivery.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/courier/delivery")
@RequiredArgsConstructor
public class CourierDeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    // 修改快递单状态 ： 权限3，4，5(/status)
    @RequestMapping(value = "status", method = RequestMethod.PUT)
    @ResponseBody
    public void updateDeliveryStatus(@RequestParam BigInteger delivery_id, @RequestParam Integer delivery_status){
        if (delivery_status.equals(3) | delivery_status.equals(4) | delivery_status.equals(5)){
            deliveryService.updateDeliveryStatus(delivery_id, delivery_status);
        }
    }

}
