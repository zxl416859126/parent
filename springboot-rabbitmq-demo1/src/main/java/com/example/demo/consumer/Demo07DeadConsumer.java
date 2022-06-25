package com.example.demo.consumer;

import com.example.demo.message.Demo07Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = Demo07Message.DEAD_QUEUE)
@Slf4j
public class Demo07DeadConsumer {

    /**
     * 消费死信队列消息
     * @param message
     */
    @RabbitHandler
    public  void onMessage(Demo07Message message){
        log.info("消费死信队列消息{}",message);
    }
}
