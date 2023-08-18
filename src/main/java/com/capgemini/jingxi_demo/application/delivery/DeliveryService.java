package com.capgemini.jingxi_demo.application.delivery;

import com.capgemini.jingxi_demo.infrastructure.entity.DeliveryEntity;
import com.capgemini.jingxi_demo.infrastructure.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    // 添加新快递单到快递表
    public BigInteger creatDeliveryOrder(){
        DeliveryEntity deliveryEntity = new DeliveryEntity();
        DeliveryEntity result = deliveryRepository.save(deliveryEntity);
        return BigInteger.valueOf(result.getId());
    }

    // 根据delivery_id更新快递单的order_id
    public void updateOrderId(BigInteger order_id, BigInteger delivery_id){
        deliveryRepository.ModifyOrderIdById(delivery_id, order_id);
    }

    // 根据Delivery_Status更新快递状态  controller: 1 for 用户； 2 for admin； 3 for 快递员
    public boolean updateDeliveryStatus(BigInteger delivery_id, Integer delivery_status){
        Integer now_status = deliveryRepository.findById(delivery_id.longValue()).get().getDelivery_status().code;
        if (now_status < delivery_status){
            deliveryRepository.ModifyDelivery_statusBydeliveryId(delivery_status-1,delivery_id);
            System.out.println("快递状态已更新");
            return true;
        }else {
            System.out.println("快递状态无法向后更新");
            return false;
        }

    }

    public Optional<DeliveryEntity> findById(Long delivery_id){
        return deliveryRepository.findById(delivery_id);
    }



}
