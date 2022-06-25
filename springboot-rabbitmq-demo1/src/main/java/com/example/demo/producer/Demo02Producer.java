package com.example.demo.producer;

import com.example.demo.message.Demo01Message;
import com.example.demo.message.Demo02Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class Demo02Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public  void syncSend(int id){
        Demo02Message message = new Demo02Message();
        message.setId(id);
        //同步发送消息，rabbitTemplate 发送消息使用jdk 的序列化方式
        rabbitTemplate.convertAndSend(Demo02Message.EXCHANGE,"zxl.zxl.test",message);
    }

}
