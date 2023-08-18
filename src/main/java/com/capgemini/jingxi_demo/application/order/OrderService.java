package com.capgemini.jingxi_demo.application.order;

import com.capgemini.jingxi_demo.infrastructure.entity.*;
import com.capgemini.jingxi_demo.infrastructure.repository.DeliveryRepository;
import com.capgemini.jingxi_demo.infrastructure.repository.OrderDetailRepository;
import com.capgemini.jingxi_demo.infrastructure.repository.OrderRepository;
import com.capgemini.jingxi_demo.infrastructure.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import java.sql.Timestamp;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private ProductRepository productRepository;

    // 添加新订单到订单表, 和订单详情表, 创建快递单
    public BigInteger creatOrder(List<ShoppingCartEntity> list, BigInteger delivery_id){
        OrderEntity orderEntity = new OrderEntity();
        BigDecimal amount = new BigDecimal (0);
        BigInteger user_id = null;

        for (ShoppingCartEntity i:list){
            amount = amount.add(i.getAmount());
            user_id = i.getUser_id();
        }

        orderEntity.setAmount(amount);
        orderEntity.setUser_id(user_id);
        orderEntity.setDelivery_id(delivery_id);

        OrderEntity result = orderRepository.save(orderEntity);
        return BigInteger.valueOf(result.getId());
    }

    // 查看该用户的所有订单信息
    public List<OrderEntity> findByUser_id(BigInteger userid){
        return orderRepository.findByUser_id(userid);
    }

    // 支付订单: 查询订单状态是否未支付，修改状态，锁定库存，保存checkout时间。
    public void checkoutOrder(BigInteger orderid){
        if (orderRepository.findById(orderid.longValue()).isPresent()){
            if (orderRepository.findById(orderid.longValue()).get().getPay_status().code.equals(1)){
                Date date = new Date();
                Timestamp nowdate = new Timestamp(date.getTime());
                orderRepository.Modifycheckout_timeById(nowdate,orderid.longValue());
                orderRepository.ModifyPay_statusById(1,orderid.longValue());
                List<OrderDetailEntity> list = orderDetailRepository.findByOrder_id(orderid);
                for (OrderDetailEntity i:list){
                    Long id = i.getProduct_id().longValue();
                    Integer lock = i.getQuantity() + productRepository.findById(id).get().getQuantity_locked();
                    productRepository.ModifyProductQuantity_lockedById(id,lock);
                }
                System.out.println("订单支付完成");
            }else if(orderRepository.findById(orderid.longValue()).get().getPay_status().code.equals(3)){
                System.out.println("订单已被取消, 无法支付");
            }else {
                System.out.println("订单已支付，无法重复支付");
            }
        }else {
            System.out.println("订单不存在");
        }
    }

    // 在未发货的情况下可以撤销订单。 减少对应的lock库存
    public void cancelOrder(BigInteger orderid){
        Optional<DeliveryEntity> d = deliveryRepository.findByOrder_id(orderid);
        if (d.isPresent()){
            if (d.get().getDelivery_status().code.equals(1) | d.get().getDelivery_status().code.equals(2)){
                Date date = new Date();
                Timestamp timenow = new Timestamp(date.getTime());
                orderRepository.Modifycancel_timeById(timenow, orderid.longValue());
                deliveryRepository.ModifyDelivery_statusById(6,orderid);
                orderRepository.ModifyPay_statusById(2,orderid.longValue());

                // 解锁库存
                List<OrderDetailEntity> list = orderDetailRepository.findByOrder_id(orderid);
                for (OrderDetailEntity i:list){
                    Long id = i.getProduct_id().longValue();
                    Integer unlock = productRepository.findById(id).get().getQuantity_locked() - i.getQuantity();
                    productRepository.ModifyProductQuantity_lockedById(id, unlock);
                }

                System.out.println("订单已撤销");
            }else if (d.get().getDelivery_status().code.equals(7)){
                System.out.println("订单已撤销，无法重复撤销订单");
            }else {
                System.out.println("快递已发货，无法撤销订单");
            }
        }else {
            System.out.println("还未结算订单，无需撤销");
        }
    }

    // 修改订单状态（仅限于货物到达之后的签收）减少库存
    public void updateOrder(BigInteger orderid){
        Date date = new Date();
        Timestamp timenow = new Timestamp(date.getTime());
        orderRepository.Modifyfinish_timeById(timenow, orderid.longValue());

        // 恢复锁定库存，并减少实际库存
        List<OrderDetailEntity> list = orderDetailRepository.findByOrder_id(orderid);
        for (OrderDetailEntity i:list){
            Long id = i.getProduct_id().longValue();
            Integer unlock = productRepository.findById(id).get().getQuantity_locked() - i.getQuantity();
            Integer quantity = productRepository.findById(id).get().getQuantity() - i.getQuantity();
            productRepository.ModifyProductQuantity_lockedById(id, unlock);
            productRepository.ModifyProductQuantityById(id, quantity);
        }

    }

}
