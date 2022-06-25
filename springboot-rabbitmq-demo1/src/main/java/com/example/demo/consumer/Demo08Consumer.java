package com.example.demo.consumer;

import com.example.demo.message.Demo08Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {Demo08Message.DELAY_QUEUE} )
@Slf4j
public class Demo08Consumer {


    /**
     * 消费延迟队列
     * @param message
     */
    @RabbitHandler
    public void onMessage(Demo08Message message){
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

}
