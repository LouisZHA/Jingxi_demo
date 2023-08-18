package com.capgemini.jingxi_demo.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DeliveryStatusEnum {
    Order_receiving(1, "接单中"),
    Order_received(2,"已接单"),
    In_transit(3, "运输中"),
    Dispatch(4, "派送中"),
    Delivered(5, "已送达"),
    Signed(6, "已签收"),
    Canceled(7,"已取消"),
    Lost(8, "丢失");

    public final Integer code;
    public final String des;

}