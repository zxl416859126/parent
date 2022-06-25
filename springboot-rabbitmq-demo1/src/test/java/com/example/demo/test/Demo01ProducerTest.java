package com.example.demo.test;

import com.example.demo.SpringbootRabbitmqDemo1Application;
import com.example.demo.producer.Demo01Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootRabbitmqDemo1Application.class)
@Slf4j
public class Demo01ProducerTest {

    @Autowired
    private Demo01Producer demo01Producer;

    @Test
    public void testSyncSend(){
        int id =1;
        demo01Producer.syncSend(1);
        log.info("发送消息编号{} 成功",id);
    }

    @Test
    public void testSyncDefaultSend(){
        int id = (int) System.currentTimeMillis() /1000;
        demo01Producer.syncDefaultSend(id);
        log.info("发送消息编号{} 成功",id);
    }

    @Test
    public  void testAsyncSend(){
        int id = (int) System.currentTimeMillis() /1000;
        demo01Producer.asyncSend(id);
        log.info("异步发送消息编号{} 成功",id);
    }
}
