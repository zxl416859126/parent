package com.example.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RPCClient {
    private Connection connection;
    private Channel channel;
    private String requestQueueName = "rpc_queue";
    private String replyQueueName;
    private QueueingConsumer consumer;
    private  static  final  String ip = "127.0.0.1";
    private  static  final int port = 5672;

    public RPCClient() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(ip);
        factory.setPort(port);
        factory.setUsername("guest");
        factory.setPassword("guest");
        connection = factory.newConnection();
        channel = connection.createChannel();
        replyQueueName = channel.queueDeclare().getQueue();
        System.out.println("设置回调队列" + replyQueueName);
        consumer = new QueueingConsumer(channel);
        channel.basicConsume(replyQueueName,true,consumer);
    }
    public String call(String message) throws  Exception{
        String uuid = String.valueOf(System.currentTimeMillis());
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .correlationId(uuid).replyTo(replyQueueName).build();
        channel.basicPublish("",requestQueueName,properties,message.getBytes());
        while (true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            if (delivery.getProperties().getCorrelationId().equals(uuid)) {
                System.out.println("收到响应" + new String(delivery.getBody()));
               break;
            }
        }
        return "";
    }

    public static void main(String[] args) throws Exception {
        RPCClient rpcClient = new RPCClient();
        rpcClient.call("zxl");
    }
}
