package com.example.demo.test;

import com.example.demo.SpringbootRabbitmqDemo1Application;
import com.example.demo.common.MessageSenderImpl;
import com.example.demo.producer.Demo05Producer;
import com.example.demo.producer.Demo07Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootRabbitmqDemo1Application.class)
@Slf4j
public class Demo07ProducerTest {

    @Autowired
    private Demo07Producer demo07Producer;
    @Autowired
    private MessageSenderImpl messageSender;

    @Test
    public void testSyncSend() throws  Exception{
        //正常发送，测试消费端消费失败重试
        int id =(int) System.currentTimeMillis() /1000;
        demo07Producer.syncSend(id);
        log.info("发送消息编号{} 成功",id);
    }

    @Test
    public void testSyncSendRetry() throws  Exception{
        //测试发送重试，模拟没有序列化，发送失败，
        int id =(int) System.currentTimeMillis() /1000;
        demo07Producer.syncSendRetry(id);
        log.info("发送消息编号{} 成功",id);
    }


}
