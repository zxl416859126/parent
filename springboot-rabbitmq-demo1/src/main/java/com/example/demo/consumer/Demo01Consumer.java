package com.example.demo.consumer;

import com.example.demo.message.Demo01Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = Demo01Message.QUEUE)
@Slf4j
public class Demo01Consumer {

    @RabbitHandler
    public  void  onMessage(Demo01Message message){
        log.info("线程 {},id {} 处理消息{}",Thread.currentThread(),Thread.currentThread().getId(),message);
    }

    @RabbitHandler(isDefault = true)
    public  void  onDefaultMessage(Message message){
        log.info("线程 {},id {} 处理消息{}",Thread.currentThread(),Thread.currentThread().getId(),message);
    }

}
