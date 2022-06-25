package com.example.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RabbitMqConsumer {
    private  static  final  String queue_name = "queue_demo";
    private  static  final  String ip = "127.0.0.1";
    private  static  final int port = 5672;

    public static void main(String[] args) throws  Exception {
        Address[] addresses = new Address[]{new Address(ip,port)};
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection(queue_name);
        Channel channel = connection.createChannel();
        channel.basicQos(64);//最多接收未被ack的消息个数
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                System.out.println("recv message" + new String(body));
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
                //ack 确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(queue_name,consumer);
        TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();


    }
}
