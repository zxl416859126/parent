package com.example.demo.common;

import com.example.demo.cache.RetryCache;
import com.example.demo.message.Demo06Message;
import com.example.demo.message.Demo07Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 生产者发送消息失败重试
 */
@Service
@Slf4j
public class MessageSenderImpl implements MessageSender{
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private RetryCache  retryCache = new RetryCache();

    private  void  configRabbitTemplate(){
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                log.info("send message failed: " + cause + correlationData.toString());
            } else {
                retryCache.del(Integer.valueOf(correlationData.getId()));
            }
        });
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, tmpExchange, tmpRoutingKey) -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("send message failed: " + replyCode + " " + replyText);
            rabbitTemplate.send(message);
        });
    }

    @Override
    public DetailResponse send(Integer id, Object message) {
        retry();
        retryCache.add(id,message);
        rabbitTemplate.convertAndSend(Demo07Message.EXCHANGE,Demo07Message.ROUTING_KEY,message);
        return null;
    }

    @Override
    public DetailResponse send(RetryCache.MessageWithTime messageWithTime) {
        return null;
    }

    private void retry(){
        new Thread(() ->{
            while (true) {
                try {
                    Thread.sleep(Constants.RETRY_TIME_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long now = System.currentTimeMillis();
                for (Integer key : retryCache.getMap().keySet()) {
                    RetryCache.MessageWithTime messageWithTime = retryCache.getMap().get(key);
                    if (null != messageWithTime) {
                        if (messageWithTime.getTime() + 3 * Constants.VALID_TIME < now) {
                            log.info("send message failed after 3 min " + messageWithTime);
                            retryCache.del(key);
                        } else if (messageWithTime.getTime() + Constants.VALID_TIME < now) {
                            DetailResponse detailRes = this.send(messageWithTime);
                            if (detailRes.isSuccess()) {
                                retryCache.del(key);
                            }
                        }
                    }
                }
            }
        }).start();
    }
}
