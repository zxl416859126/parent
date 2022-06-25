package com.example.demo.consumer;

import com.example.demo.message.Demo04Message;
import com.example.demo.message.Demo05Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RabbitListener(queues = {Demo05Message.QUEUE},
        containerFactory = "consumerBatchContainerFactory")
@Slf4j
public class Demo05Consumer {

    @RabbitHandler
    public  void  onMessage(Demo05Message message){
        log.info("线程 {},id {} 处理消息{}",Thread.currentThread(),Thread.currentThread().getId(),message);
    }

    /**
     * 第一种批量消费消息方式，依赖 生产者批量发送
     * @param messages
     */
//    @RabbitHandler
//    public void onMessage(List<Demo05Message> messages){
//        log.info("线程{}消费消息，消息数量{}",Thread.currentThread(),messages.size());
//    }


}
