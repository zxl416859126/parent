package com.example.demo.producer;

import com.example.demo.message.Demo02Message;
import com.example.demo.message.Demo03Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo03Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public  void syncSend(int id){
        Demo03Message message = new Demo03Message();
        message.setId(id);
        //同步发送消息，rabbitTemplate 发送消息使用jdk 的序列化方式
        rabbitTemplate.convertAndSend(Demo03Message.EXCHANGE,"1111",message);
    }

}
