package com.example.demo.config;

import com.example.demo.message.Demo01Message;
import com.example.demo.message.Demo02Message;
import com.example.demo.message.Demo03Message;
import com.example.demo.message.Demo04Message;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    public  static class DirectExchangeDemoConfig{

        @Bean
        public Queue demo01Queue(){
            /**
             * boolean durable, 是否持久化
             * boolean exclusive, 是否排他
             * boolean autoDelete 是否自动删除
             */
            return new Queue(Demo01Message.QUEUE,true,false,false);
        }

        @Bean
        public Exchange  directExchange(){
            /**
             * boolean durable, 是否持久化
             * boolean autoDelete 是否自动删除
             */
            return new DirectExchange(Demo01Message.EXCHANGE,true,false);
        }

        @Bean
        public Binding binding(){
            return BindingBuilder.bind(demo01Queue())
                    .to(directExchange())
                    .with(Demo01Message.ROUTING_KEY).noargs();
        }
    }

    public  static  class TopicExchangeDemoConfig{
        @Bean
        public Queue demo02Queue(){
            /**
             * boolean durable, 是否持久化
             * boolean exclusive, 是否排他
             * boolean autoDelete 是否自动删除
             */
            return new Queue(Demo02Message.QUEUE,true,false,false);
        }

        @Bean
        public Exchange  topicExchange(){
            /**
             * boolean durable, 是否持久化
             * boolean autoDelete 是否自动删除
             */
            return new TopicExchange(Demo02Message.EXCHANGE,true,false);
        }

        @Bean(name = "topicBinding")
        public Binding binding(){
            return BindingBuilder.bind(demo02Queue())
                    .to(topicExchange())
                    .with(Demo02Message.ROUTING_KEY).noargs();
        }
    }

    /**
     * fanout Config
     */
    public  static  class FanoutExchangeDemoConfig{
        @Bean
        public Queue demo03QueueA(){
            /**
             * boolean durable, 是否持久化
             * boolean exclusive, 是否排他
             * boolean autoDelete 是否自动删除
             */
            return new Queue(Demo03Message.QUEUE_A,true,false,false);
        }
        @Bean
        public Queue demo03QueueB(){
            /**
             * boolean durable, 是否持久化
             * boolean exclusive, 是否排他
             * boolean autoDelete 是否自动删除
             */
            return new Queue(Demo03Message.QUEUE_B,true,false,false);
        }

        @Bean
        public Exchange  fanoutExchange(){
            /**
             * boolean durable, 是否持久化
             * boolean autoDelete 是否自动删除
             */
            return new FanoutExchange(Demo03Message.EXCHANGE,true,false);
        }

        @Bean
        public Binding demo03ABinding(){
            return BindingBuilder.bind(demo03QueueB())
                    .to(fanoutExchange()).with("").noargs();
        }

        @Bean
        public Binding demo03Binding(){
            return BindingBuilder.bind(demo03QueueA())
                    .to(fanoutExchange()).with("").noargs();
        }
    }

    /**
     * header config
     */
    public  static  class HeaderExchangeDemoConfig{
        @Bean
        public Queue demo04Queue(){
            /**
             * boolean durable, 是否持久化
             * boolean exclusive, 是否排他
             * boolean autoDelete 是否自动删除
             */
            return new Queue(Demo04Message.QUEUE,true,false,false);
        }


        @Bean
        public HeadersExchange  headerExchange(){
            /**
             * boolean durable, 是否持久化
             * boolean autoDelete 是否自动删除
             */
            return new HeadersExchange(Demo04Message.EXCHANGE,true,false);
        }

        @Bean
        public Binding demo04Binding(){
            return BindingBuilder.bind(demo04Queue()).to(headerExchange())
                    .where(Demo04Message.HEADER_KEY).matches(Demo04Message.HEADER_VALUE);
        }


    }

}
