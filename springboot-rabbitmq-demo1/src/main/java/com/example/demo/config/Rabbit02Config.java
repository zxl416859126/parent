package com.example.demo.config;

import com.example.demo.message.*;
import org.springframework.amqp.core.*;
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

/**
 * 批量发送消息
 */
@Configuration
public class Rabbit02Config {

    /**
     * 批量发送消息配置
     */
    public  static class DirectExchangeDemoConfig{

        @Bean
        public Queue demo05Queue(){
            /**
             * boolean durable, 是否持久化
             * boolean exclusive, 是否排他
             * boolean autoDelete 是否自动删除
             */
            return new Queue(Demo05Message.QUEUE,true,false,false);
        }

        @Bean
        public DirectExchange  batchingDirectExchange(){
            /**
             * boolean durable, 是否持久化
             * boolean autoDelete 是否自动删除
             */
            return new DirectExchange(Demo05Message.EXCHANGE,true,false);
        }

        @Bean
        public Binding batchingBinding(){
            return BindingBuilder.bind(demo05Queue())
                    .to(batchingDirectExchange())
                    .with(Demo05Message.ROUTING_KEY);
        }

        /**
         * 定义一个批量发送消息的模版
         * @param connectionFactory
         * @return
         */
        @Bean
        public BatchingRabbitTemplate batchRabbitTemplate(ConnectionFactory connectionFactory) {
            // 创建 BatchingStrategy 对象，代表批量策略
            int batchSize = 16384; // 超过收集的消息数量的最大条数。
            int bufferLimit = 33554432; // 每次批量发送消息的最大内存
            int timeout = 30000; // 超过收集的时间的最大等待时长，单位：毫秒
            BatchingStrategy batchingStrategy = new SimpleBatchingStrategy(batchSize, bufferLimit, timeout);

            // 创建 TaskScheduler 对象，用于实现超时发送的定时器
            TaskScheduler taskScheduler = new ConcurrentTaskScheduler();
            // 创建 BatchingRabbitTemplate 对象
            BatchingRabbitTemplate batchTemplate = new BatchingRabbitTemplate(batchingStrategy, taskScheduler);
            batchTemplate.setConnectionFactory(connectionFactory);
            return batchTemplate;
        }

        /**
         *
         */
        @Bean("consumerBatchContainerFactory")
        public SimpleRabbitListenerContainerFactory consumerBatchContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
                                                                                  ConnectionFactory connectionFactory){
            // 创建 SimpleRabbitListenerContainerFactory 对象
            SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
            configurer.configure(factory, connectionFactory);
            // <X> 额外添加批量消费的属性
            factory.setBatchListener(true);
            return factory;
        }
    }

}
