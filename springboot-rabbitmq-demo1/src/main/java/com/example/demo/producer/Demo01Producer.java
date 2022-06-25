package com.example.demo.producer;

import com.example.demo.message.Demo01Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureTask;

@Component
public class Demo01Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public  void syncSend(int id){
        Demo01Message message = new Demo01Message();
        message.setId(id);
        //同步发送消息，rabbitTemplate 发送消息使用jdk 的序列化方式
        rabbitTemplate.convertAndSend(Demo01Message.EXCHANGE,Demo01Message.ROUTING_KEY,message);
    }

    public void syncDefaultSend(int id){
        Demo01Message message = new Demo01Message();
        message.setId(id);
        //同步发送消息，使用rabbitmq 默认交换机(隐式绑定到每个队列) ，使用队列名字作为路由键
        rabbitTemplate.convertAndSend(Demo01Message.QUEUE,message);
    }

    @Async
    public ListenableFuture<Void> asyncSend(int id){
        try {
            this.syncSend(id);
            return AsyncResult.forValue(null);
        } catch (Exception e) {
           return AsyncResult.forExecutionException(e);
        }
    }

}
