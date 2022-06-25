package com.example.demo.config;

import com.example.demo.message.Demo06Message;
import com.example.demo.message.Demo07Message;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息重试
 */
@Configuration
public class RabbitConfig07 {
    /**
     * Direct Exchange 示例的配置类
     */
    public static class DirectExchangeDemoConfiguration {

        // 创建 Queue
        @Bean
        public Queue demo07Queue() {
            Map<String,Object> argss = new HashMap<>();
            argss.put("x-dead-letter-exchange",Demo07Message.EXCHANGE);
            argss.put("x-dead-letter-routing-key",Demo07Message.DEAD_ROUTING_KEY);
            Queue queue = new Queue(Demo07Message.QUEUE,true,false,false,argss);
            return queue;
//            return QueueBuilder.durable(Demo07Message.QUEUE) // durable: 是否持久化
//                     // exclusive: 是否排它
//                    // autoDelete: 是否自动删除
//                    .deadLetterExchange(Demo07Message.EXCHANGE)
//                    .deadLetterRoutingKey(Demo07Message.DEAD_ROUTING_KEY)
//                    .build();
        }

        // 创建 Dead Queue
        @Bean
        public Queue demo07DeadQueue() {
            return new Queue(Demo07Message.DEAD_QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }

        // 创建 Direct Exchange
        @Bean
        public DirectExchange demo07Exchange() {
            return new DirectExchange(Demo07Message.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }

        // 创建 Binding
        // Exchange：Demo07Message.EXCHANGE
        // Routing key：Demo07Message.ROUTING_KEY
        // Queue：Demo07Message.QUEUE
        @Bean
        public Binding demo07Binding() {
            return BindingBuilder.bind(demo07Queue()).to(demo07Exchange()).with(Demo07Message.ROUTING_KEY);
        }

        // 创建 Dead Binding
        // Exchange：Demo07Message.EXCHANGE
        // Routing key：Demo07Message.DEAD_ROUTING_KEY
        // Queue：Demo07Message.DEAD_QUEUE
        @Bean
        public Binding demo07DeadBinding() {
            return BindingBuilder.bind(demo07DeadQueue())
                    .to(demo07Exchange()).with(Demo07Message.DEAD_ROUTING_KEY);
        }

    }

}
