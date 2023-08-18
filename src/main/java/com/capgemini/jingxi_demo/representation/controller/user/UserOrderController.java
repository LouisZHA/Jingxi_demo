package com.capgemini.jingxi_demo.representation.controller.user;

import com.capgemini.jingxi_demo.application.delivery.DeliveryService;
import com.capgemini.jingxi_demo.application.order.OrderDetailService;
import com.capgemini.jingxi_demo.application.order.OrderService;
import com.capgemini.jingxi_demo.application.order.dto.OrderAddDTO;
import com.capgemini.jingxi_demo.application.order.dto.mapping.OrderDetailMapping;
import com.capgemini.jingxi_demo.application.shoppingcart.ShoppingCartService;
import com.capgemini.jingxi_demo.application.shoppingcart.dto.ShoppingCartDTO;
import com.capgemini.jingxi_demo.infrastructure.entity.OrderDetailEntity;
import com.capgemini.jingxi_demo.infrastructure.entity.OrderEntity;
import com.capgemini.jingxi_demo.infrastructure.entity.ShoppingCartEntity;
import com.capgemini.jingxi_demo.infrastructure.repository.OrderRepository;
import com.capgemini.jingxi_demo.infrastructure.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/user/order")
@RequiredArgsConstructor
public class UserOrderController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private DeliveryService deliveryService;


    // 结算购物车并创建订单，锁定对应商品的库存数量，计算总价
    @RequestMapping(value = "creat", method = RequestMethod.POST)
    @ResponseBody
    public void creatOrder(@RequestParam BigInteger userid){

        List<ShoppingCartEntity> list = shoppingCartService.showShoppingCart(userid);
        if(list != null && !list.isEmpty()){
            // 创建快递单，并返回快递单id
            BigInteger delivery_id = deliveryService.creatDeliveryOrder();

            // 创建订单，并返回订单id
            BigInteger order_id = orderService.creatOrder(list, delivery_id);

            // 根据delivery_id更新快递单的order_id
            deliveryService.updateOrderId(order_id,delivery_id);

            // 根据购物车列表添加商品到订单详情表
            orderDetailService.addOrderDetail(list, order_id);

            // 清空购物车
            shoppingCartService.cleanShoppingCart(userid);

            System.out.println("订单id: " + order_id);
            System.out.println("快递单id: " +  delivery_id);
        }

    }

    // 支付订单 开启快递 锁定库存
    @RequestMapping(value = "checkout", method = RequestMethod.PUT)
    @ResponseBody
    public void checkoutOrder(@RequestParam BigInteger orderid){
        orderService.checkoutOrder(orderid);
    }

    // 查询订单状态，在未发货的情况下可以撤销订单。
    @RequestMapping(value = "cancel", method = RequestMethod.PUT)
    @ResponseBody
    public void cancelOrder(@RequestParam BigInteger orderid){
        orderService.cancelOrder(orderid);
    }

    // 查看该用户的所有订单信息
    @RequestMapping(value = "showall", method = RequestMethod.GET)
    @ResponseBody
    public List<OrderEntity> showOrder(@RequestParam BigInteger userid){
        return orderService.findByUser_id(userid);
    }

    // 用户：订单id
    // 查询出订单信息、订单中所有货物的信息、和订单的物流信息
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public List<OrderDetailEntity> getOrderDetail(@RequestParam BigInteger orderid){
        return orderDetailService.findDetailbyOrder_id(orderid);
    }


}
