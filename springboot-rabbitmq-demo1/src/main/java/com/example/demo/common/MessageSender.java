package com.example.demo.common;

import com.example.demo.cache.RetryCache;

public interface MessageSender {
    DetailResponse send(Integer id,Object message);
    DetailResponse send(RetryCache.MessageWithTime messageWithTime);
}
