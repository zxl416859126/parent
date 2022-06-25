package com.example.demo.producer;

import com.example.demo.common.MessageSenderImpl;
import com.example.demo.message.Demo08Message;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo08Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public  void syncSend(int id){
        Demo08Message message = new Demo08Message();
        message.setId(id);
        //同步发送消息，rabbitTemplate 发送消息使用jdk 的序列化方式
        rabbitTemplate.convertAndSend(Demo08Message.EXCHANGE,Demo08Message.ROUTING_KEY,message);
    }
}
