package com.example.demo.message;

import lombok.Data;

import java.io.Serializable;

/**
 * direct messgae
 */
@Data
public class Demo01Message implements Serializable {
    private static final long serialVersionUID = -621277979830209469L;

    public static final String QUEUE = "QUEUE_DEMO_01";

    public static final String EXCHANGE = "EXCHANGE_DEMO_01";

    public static final String ROUTING_KEY = "ROUTING_KEY_01";


    /**
     * 编号
     */
    private Integer id;
}
