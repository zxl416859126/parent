package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailResponse
{
    private boolean success;

    private String errorCode;

    private String errMsg;

    public static DetailResponse success(String errMsg)
    {
        return new DetailResponse(true, null, errMsg);
    }
}
