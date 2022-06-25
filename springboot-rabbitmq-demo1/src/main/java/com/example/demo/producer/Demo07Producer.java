package com.example.demo.producer;

import com.example.demo.cache.RetryCache;
import com.example.demo.common.MessageSenderImpl;
import com.example.demo.message.Demo01Message;
import com.example.demo.message.Demo07Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class Demo07Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageSenderImpl messageSender;


    public  void syncSend(int id){
        Demo07Message message = new Demo07Message();
        message.setId(id);
        //同步发送消息，rabbitTemplate 发送消息使用jdk 的序列化方式
        rabbitTemplate.convertAndSend(Demo07Message.EXCHANGE,Demo07Message.ROUTING_KEY,message);
    }

    /**
     * 生产者发送消息失败重试
     * @param id
     */

    public  void syncSendRetry(int id){
        Demo07Message message = new Demo07Message();
        message.setId(id);
        //同步发送消息，rabbitTemplate 发送消息使用jdk 的序列化方式
        messageSender.send(id,message);
    }



}
