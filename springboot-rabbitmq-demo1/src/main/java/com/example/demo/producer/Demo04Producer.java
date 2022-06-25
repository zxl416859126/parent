package com.example.demo.producer;

import com.example.demo.message.Demo04Message;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo04Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public  void syncSend(int id,String headValue){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader(Demo04Message.HEADER_KEY,headValue);
        Demo04Message message = new Demo04Message();
        message.setId(id);
        Message toMessage = rabbitTemplate.getMessageConverter().toMessage(message,messageProperties);
        rabbitTemplate.send(Demo04Message.EXCHANGE,"",toMessage);
    }
}
