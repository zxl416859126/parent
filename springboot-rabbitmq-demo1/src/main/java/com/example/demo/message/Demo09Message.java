package com.example.demo.message;

// Demo08Message.java

import lombok.Data;

import java.io.Serializable;

@Data
public class Demo09Message implements Serializable {

    public static final String DELAY_QUEUE = "DELAY_QUEUE_DEMO_09"; // 延迟队列（死信队列）

    public static final String EXCHANGE = "EXCHANGE_DEMO_09";

    public static final String DELAY_ROUTING_KEY = "DELAY_ROUTING_KEY_09"; // 延迟路由键（死信路由键）
    private static final long serialVersionUID = 4502506995381222526L;


    /**
     * 编号
     */
    private Integer id;

}