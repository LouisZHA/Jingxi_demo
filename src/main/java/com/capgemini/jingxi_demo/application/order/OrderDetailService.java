package com.capgemini.jingxi_demo.application.order;

import com.capgemini.jingxi_demo.application.order.dto.mapping.OrderDetailMapping;
import com.capgemini.jingxi_demo.infrastructure.entity.OrderDetailEntity;
import com.capgemini.jingxi_demo.infrastructure.entity.ShoppingCartEntity;
import com.capgemini.jingxi_demo.infrastructure.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailMapping orderDetailMapping;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    // 根据购物车列表，添加商品到订单详情表
    public void addOrderDetail(List<ShoppingCartEntity> ShoppingCartList, BigInteger order_id){
        List<OrderDetailEntity> list = orderDetailMapping.toOrderDetailEntitylist(ShoppingCartList);
        list.forEach(orderdetail -> orderdetail.setOrder_id(order_id));
        orderDetailRepository.saveAll(list);
    }

    public List<OrderDetailEntity> findDetailbyOrder_id(BigInteger order_id){
        return orderDetailRepository.findByOrder_id(order_id);
    }

}
