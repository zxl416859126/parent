package com.example.demo.consumer;

import com.example.demo.message.Demo01Message;
import com.example.demo.message.Demo02Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = Demo02Message.QUEUE)
@Slf4j
public class Demo02Consumer {

    @RabbitHandler
    public  void  onMessage(Demo02Message message){
        log.info("线程 {},id {} 处理消息{}",Thread.currentThread(),Thread.currentThread().getId(),message);
    }


}
