package com.example.demo.consumer;

import com.example.demo.message.Demo03Message;
import com.example.demo.message.Demo04Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {Demo04Message.QUEUE})
@Slf4j
public class Demo04Consumer {

    @RabbitHandler
    public  void  onMessage(Demo04Message message){
        log.info("线程 {},id {} 处理消息{}",Thread.currentThread(),Thread.currentThread().getId(),message);
    }


}
