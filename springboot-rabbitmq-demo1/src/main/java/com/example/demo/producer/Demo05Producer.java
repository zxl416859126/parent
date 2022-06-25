package com.example.demo.producer;

import com.example.demo.message.Demo04Message;
import com.example.demo.message.Demo05Message;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo05Producer {
    @Autowired
    private BatchingRabbitTemplate batchingRabbitTemplater;

    public  void syncSend(int id){
        Demo05Message message = new Demo05Message();
        message.setId(id);
        batchingRabbitTemplater.convertAndSend(Demo05Message.EXCHANGE,
                Demo05Message.ROUTING_KEY,message);
    }
}
