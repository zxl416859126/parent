package com.example.demo.message;

import lombok.Data;

import java.io.Serializable;

/**
 * Fanout message
 */
@Data
public class Demo03Message implements Serializable {
    private static final long serialVersionUID = -4435116003493085099L;

    public static final String QUEUE_A = "QUEUE_DEMO_03_A";
    public static final String QUEUE_B = "QUEUE_DEMO_03_B";
    public static final String EXCHANGE = "EXCHANGE_DEMO_03";

    /**
     * 编号
     */
    private Integer id;

}
