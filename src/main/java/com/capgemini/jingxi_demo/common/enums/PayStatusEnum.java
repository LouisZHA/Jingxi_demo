package com.capgemini.jingxi_demo.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PayStatusEnum {
    Unpaid(1, "未支付"),
    Paid(2,"已支付"),
    Canceled(3, "已取消");

    public final Integer code;
    public final String des;

}
