package com.example.demo.message;

import lombok.Data;

import java.io.Serializable;

@Data
public class Demo07Message  {
    
    public static final String QUEUE = "QUEUE_DEMO_07_ordinary"; // 正常队列
    public static final String DEAD_QUEUE = "DEAD_QUEUE_DEMO_07"; // 死信队列

    public static final String EXCHANGE = "EXCHANGE_DEMO_07";

    public static final String ROUTING_KEY = "ROUTING_KEY_07"; // 正常路由键
    public static final String DEAD_ROUTING_KEY = "DEAD_ROUTING_KEY_07"; // 死信路由键
    //private static final long serialVersionUID = -1632869890096886693L;


    /**
     * 编号
     */
    private Integer id;
}
