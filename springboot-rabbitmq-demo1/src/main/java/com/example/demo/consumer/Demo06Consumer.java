package com.example.demo.consumer;

import com.example.demo.message.Demo05Message;
import com.example.demo.message.Demo06Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RabbitListener(queues = {Demo06Message.QUEUE},
        containerFactory = "consumerBatchContainerFactory2")
@Slf4j
public class Demo06Consumer {


    /**
     * 第二种批量消费消息方式
     * @param messages
     */
    @RabbitHandler
    public void onMessage(List<Demo06Message> messages){
        log.info("线程{}消费消息，消息数量{}",Thread.currentThread(),messages.size());
    }


}
