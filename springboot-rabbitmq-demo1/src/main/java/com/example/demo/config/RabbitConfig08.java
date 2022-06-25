package com.example.demo.config;

import com.example.demo.message.Demo07Message;
import com.example.demo.message.Demo08Message;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 延迟消息
 */
@Configuration
public class RabbitConfig08 {
    /**
     * Direct Exchange 示例的配置类
     */
    public static class DirectExchangeDemoConfiguration {

        // 创建 Queue
        @Bean
        public Queue demo08Queue() {
            Map<String,Object> argss = new HashMap<>();
            argss.put("x-dead-letter-exchange", Demo08Message.EXCHANGE); //设置死信交换机
            argss.put("x-dead-letter-routing-key",Demo08Message.DELAY_ROUTING_KEY);
            argss.put("x-message-ttl",10 * 1000);// 设置队列中消息过期时间
            Queue queue = new Queue(Demo08Message.QUEUE,true,false,false,argss);
            return queue;
        }

        // 创建 Delay Queue
        @Bean
        public Queue demo08DelayQueue() {
            return new Queue(Demo08Message.DELAY_QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }

        // 创建 Direct Exchange
        @Bean
        public DirectExchange demo08Exchange() {
            return new DirectExchange(Demo08Message.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }

        // 创建 Binding
        // Exchange：Demo08Message.EXCHANGE
        // Routing key：Demo08Message.ROUTING_KEY
        // Queue：Demo08Message.QUEUE
        @Bean
        public Binding demo08Binding() {
            return BindingBuilder.bind(demo08Queue()).to(demo08Exchange()).with(Demo08Message.ROUTING_KEY);
        }

        // 创建 Delay Binding
        // Exchange：Demo08Message.EXCHANGE
        // Routing key：Demo08Message.DELAY_ROUTING_KEY
        // Queue：Demo08Message.DELAY_QUEUE
        @Bean
        public Binding demo08DelayBinding() {
            return BindingBuilder.bind(demo08DelayQueue()).to(demo08Exchange()).with(Demo08Message.DELAY_ROUTING_KEY);
        }
    }

}
