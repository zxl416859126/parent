package com.example.demo.producer;

import com.example.demo.message.Demo01Message;
import com.example.demo.message.Demo05Message;
import com.example.demo.message.Demo06Message;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo06Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public  void syncSend(int id){
        Demo01Message message = new Demo01Message();
        message.setId(id);
        //同步发送消息，rabbitTemplate 发送消息使用jdk 的序列化方式
        rabbitTemplate.convertAndSend(Demo06Message.EXCHANGE,Demo06Message.ROUTING_KEY,message);
    }

}
