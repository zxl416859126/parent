package com.example.demo.consumer;

import com.example.demo.message.Demo06Message;
import com.example.demo.message.Demo07Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RabbitListener(queues = {Demo07Message.QUEUE} )
@Slf4j
public class Demo07Consumer {


    /**
     * 模拟消费失败，进入死信队列
     * @param message
     */
    @RabbitHandler
    public void onMessage(Demo07Message message){
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        throw  new RuntimeException("消息消费失败,抛出异常");

    }


}
