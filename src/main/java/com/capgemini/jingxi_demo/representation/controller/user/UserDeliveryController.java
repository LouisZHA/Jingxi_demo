package com.capgemini.jingxi_demo.representation.controller.user;

import com.capgemini.jingxi_demo.application.delivery.DeliveryService;
import com.capgemini.jingxi_demo.application.order.OrderService;
import com.capgemini.jingxi_demo.application.users.UserService;
import com.capgemini.jingxi_demo.infrastructure.entity.DeliveryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Optional;

@RestController
@RequestMapping("/user/delivery")
@RequiredArgsConstructor
public class UserDeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private OrderService orderService;

    // 在货物送到后，且在用户签收的时刻，修改快递单状态（签收）权限状态 6，记录时间 (/status)

    @RequestMapping(value = "status", method = RequestMethod.PUT)
    @ResponseBody
    public void updateDeliveryStatus(@RequestParam BigInteger delivery_id, @RequestParam Integer delivery_status){
        if (delivery_status.equals(6)){
            boolean b = deliveryService.updateDeliveryStatus(delivery_id, delivery_status);

            if (b){
                // 修改订单状态（仅限于货物到达之后的签收）减少库存
                Optional<DeliveryEntity> d = deliveryService.findById(delivery_id.longValue());
                BigInteger orderid = d.get().getOrder_id();
                orderService.updateOrder(orderid);
            }
        }
    }

}
