package com.example.demo.test;

import com.example.demo.SpringbootRabbitmqDemo1Application;
import com.example.demo.common.MessageSenderImpl;
import com.example.demo.producer.Demo08Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootRabbitmqDemo1Application.class)
@Slf4j
public class Demo08ProducerTest {

    @Autowired
    private Demo08Producer demo08Producer;

    /**
     *
     * @throws Exception
     */
    @Test
    public void testSyncSend() throws  Exception{
        int id =(int) System.currentTimeMillis() /1000;
        demo08Producer.syncSend(id);
        log.info("发送消息编号{} 成功",id);
    }
}
