package com.example.demo.consumer;

import com.example.demo.message.Demo09Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {Demo09Message.DELAY_QUEUE} )
@Slf4j
public class Demo09Consumer {

    /**
     * 消费延迟消息借助插件实现方式消息
     */
    @RabbitHandler
    public  void onMessage(Demo09Message message){
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
