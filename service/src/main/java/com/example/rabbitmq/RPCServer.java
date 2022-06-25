package com.example.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

public class RPCServer {
    private Connection connection;
    private Channel channel;
    private  static String  rpcQueueName = "rpc_queue";
    private QueueingConsumer consumer;
    private  static  final  String ip = "127.0.0.1";
    private  static  final int port = 5672;

    public static void main(String[] args) throws  Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(ip);
        factory.setPort(port);
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(rpcQueueName,true,false,false,null);
        channel.basicQos(1);
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                AMQP.BasicProperties replayProperties = new AMQP.BasicProperties().builder()
                        .correlationId(properties.getCorrelationId()).build();
                //响应的消息
                String reponse = new String(body)+"from_relay";

                channel.basicPublish("",properties.getReplyTo(),replayProperties,reponse.getBytes());
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(rpcQueueName,false,consumer);
    }
}
