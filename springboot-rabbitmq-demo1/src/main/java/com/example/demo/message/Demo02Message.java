package com.example.demo.message;

import lombok.Data;

import java.io.Serializable;

/**
 * topic message
 */
@Data
public class Demo02Message implements Serializable {
    private static final long serialVersionUID = -621277979830209469L;

    public static final String QUEUE = "QUEUE_DEMO_02";

    public static final String EXCHANGE = "EXCHANGE_DEMO_02";

    public static final String ROUTING_KEY = "*.test";

    /**
     * 编号
     */
    private Integer id;
}
