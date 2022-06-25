package com.example.demo.config;

import com.example.demo.message.Demo05Message;
import com.example.demo.message.Demo06Message;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.batch.BatchingStrategy;
import org.springframework.amqp.rabbit.batch.SimpleBatchingStrategy;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;


@Configuration
public class Rabbit03Config {

    /**
     * 批量消费消息配置
     */
    public  static class DirectExchangeDemoConfig{

        @Bean("demo06Queue")
        public Queue demo06Queue(){
            /**
             * boolean durable, 是否持久化
             * boolean exclusive, 是否排他
             * boolean autoDelete 是否自动删除
             */
            return new Queue(Demo06Message.QUEUE,true,false,false);
        }

        @Bean("batchingDirectExchange2")
        public DirectExchange  batchingDirectExchange(){
            /**
             * boolean durable, 是否持久化
             * boolean autoDelete 是否自动删除
             */
            return new DirectExchange(Demo06Message.EXCHANGE,true,false);
        }

        @Bean("batchingBinding2")
        public Binding batchingBinding(){
            return BindingBuilder.bind(demo06Queue())
                    .to(batchingDirectExchange())
                    .with(Demo06Message.ROUTING_KEY);
        }


        /**
         *
         */
        @Bean("consumerBatchContainerFactory2")
        public SimpleRabbitListenerContainerFactory consumerBatchContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
                                                                                  ConnectionFactory connectionFactory){
            // 创建 SimpleRabbitListenerContainerFactory 对象
            SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
            configurer.configure(factory, connectionFactory);
            // 额外添加批量消费的属性
            factory.setBatchListener(true);
            factory.setBatchSize(10);
            factory.setReceiveTimeout(30 * 1000L);
            factory.setConsumerBatchEnabled(true);
            return factory;
        }
    }

}
