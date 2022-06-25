package com.example.demo.message;

import lombok.Data;

import java.io.Serializable;

/**
 * header message
 */
@Data
public class Demo04Message implements Serializable {
    private static final long serialVersionUID = -4435116003493085099L;

    public static final String QUEUE = "QUEUE_DEMO_04_A";

    public static final String EXCHANGE = "EXCHANGE_DEMO_04";

    public static final String HEADER_KEY = "color";
    public static final String HEADER_VALUE = "red";

    /**
     * 编号
     */
    private Integer id;

}
