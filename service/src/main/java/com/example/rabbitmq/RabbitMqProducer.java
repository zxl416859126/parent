package com.example.rabbitmq;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;


public class RabbitMqProducer {
    private  static  final  String exchange_name = "exchange_demo";
    private  static  final  String routing_key = "routing_demo";
    private  static  final  String queue_name = "queue_demo";
    private  static  final  String ip = "127.0.0.1";
    private  static  final int port = 5672;

    public static void main(String[] args) throws  Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(ip);
        factory.setPort(port);
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //死信消息，延迟消息
//        channel.exchangeDeclare("exchange.dlx","direct",true);
//        channel.exchangeDeclare("exchange.normal","fanout",true);
//        Map<String,Object> argss = new HashMap<>();
//        argss.put("x-message-ttl",10000);
//        argss.put("x-dead-letter-exchange","exchange.dlx");
//        argss.put("x-dead-letter-routing-key","routingKey");
//        channel.queueDeclare("queue.normal",true,false,false,argss);
//        channel.queueBind("queue.normal","exchange.normal","normal");
//        channel.queueDeclare("queue.dlx",true,false,false,null);
//        channel.queueBind("queue.dlx","exchange.dlx","routingKey");
//        channel.basicPublish("exchange.normal",
//                "rk",
//                MessageProperties.PERSISTENT_TEXT_PLAIN,
//                "dlx".getBytes());
        //优先级消息
//        Map<String,Object> argss = new HashMap<>();
//        //设置队列优先级
//        argss.put("x-max-priority",10);
//        channel.queueDeclare("queue.priority",true,false,false,argss);
//        channel.exchangeDeclare("priority_exchange","direct");
//        channel.queueBind("queue.priority","priority_exchange","priority_key");
//        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
//        //设置单条消息优先级
//        builder.priority(5);
//        AMQP.BasicProperties basicProperties = builder.build();
//        channel.basicPublish("priority_exchange","priority_key",
//                basicProperties,"priority_message".getBytes());

        //生产者publish-confirm 确认消息
        TreeSet sortedSet = new TreeSet();
        channel.queueDeclare("publish_confirm",true,false,false,null);
        channel.exchangeDeclare("publish-confirm_exchange","direct");
        channel.queueBind("publish_confirm","publish-confirm_exchange","publish-confirm_routing_key");
        channel.confirmSelect();
        //异步confirm 方式
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                if(multiple){
                    System.out.println("交换机收到了,清理编号"+ deliveryTag +"之前的数据");
                    sortedSet.headSet(deliveryTag -1).clear();
                }else{
                    sortedSet.remove(deliveryTag);
                    System.out.println("交换机收到了,清理编号"+ deliveryTag +"数据");
                }
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                if(multiple){
                    sortedSet.headSet(deliveryTag -1).clear();
                }else{
                    sortedSet.remove(deliveryTag);
                }
            }
        });
        while(true){
            long seqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("publish-confirm_exchange",
                    "publish-confirm_routing_key",
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    String.valueOf(seqNo).getBytes());
            sortedSet.add(seqNo);
        }
//        channel.exchangeDeclare(exchange_name,"direct",true,false,null);
//        channel.queueDeclare(queue_name,true,false,false,null);
//        channel.queueBind(queue_name,exchange_name,routing_key);
//        String message = "hello,world";
////        channel.basicPublish(exchange_name,routing_key,
////                MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
//        channel.basicPublish(exchange_name,routing_key,true,
//                MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
//        channel.addReturnListener(new ReturnListener() {
//            @Override
//            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                String meessage = new String(body);
//                System.out.println("reject message" + meessage);
//            }
//        });


    }


}
