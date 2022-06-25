package com.example.demo.producer;

import com.example.demo.message.Demo08Message;
import com.example.demo.message.Demo09Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Demo09Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;



    public  void syncSend(int id){
        Demo08Message message = new Demo08Message();
        message.setId(id);
        //同步发送消息，rabbitTemplate 发送消息使用jdk 的序列化方式
        rabbitTemplate.convertAndSend(Demo08Message.EXCHANGE,Demo08Message.ROUTING_KEY,message);
    }
    /**
     * 使用rabbitmq_delayed_message_exchange 插件发送延迟消息
     */
    public void sendMsg(int id) {
        Demo09Message message = new Demo09Message();
        message.setId(id);
        log.info("发送消息成功{}",id);
        rabbitTemplate.convertAndSend(Demo09Message.EXCHANGE, Demo09Message.DELAY_ROUTING_KEY, message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //设置每个消息的延迟时间
                message.getMessageProperties().setHeader("x-delay",3000);
                return message;
            }
        });
    }

}
