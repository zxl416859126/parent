package com.example.demo.message;

import lombok.Data;

import java.io.Serializable;

/**
 * header message
 */
@Data
public class Demo06Message implements Serializable {
    private static final long serialVersionUID = -4435116003493085099L;

    public static final String QUEUE = "QUEUE_DEMO_06";

    public static final String EXCHANGE = "EXCHANGE_DEMO_06";

    public static final String ROUTING_KEY = "ROUTING_KEY_06";
    /**
     * 编号
     */
    private Integer id;

}
