package com.example.demo.config;

import com.example.demo.message.Demo09Message;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig09 {
    @Bean
    public CustomExchange delayExchange(){
        Map<String,Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(Demo09Message.EXCHANGE, "x-delayed-message",true, false,args);
    }
    @Bean
    public Queue queue() {
        Queue queue = new Queue(Demo09Message.DELAY_QUEUE, true);
        return queue;
    }

    @Bean(name = "deadBinding")
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(delayExchange())
                .with(Demo09Message.DELAY_ROUTING_KEY).noargs();
    }
}
