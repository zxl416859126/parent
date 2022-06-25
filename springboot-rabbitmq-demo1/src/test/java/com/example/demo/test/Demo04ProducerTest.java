package com.example.demo.test;

import com.example.demo.SpringbootRabbitmqDemo1Application;
import com.example.demo.message.Demo04Message;
import com.example.demo.producer.Demo03Producer;
import com.example.demo.producer.Demo04Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootRabbitmqDemo1Application.class)
@Slf4j
public class Demo04ProducerTest {

    @Autowired
    private Demo04Producer demo04Producer;

    @Test
    public void testSyncSend(){
        int id = (int)System.currentTimeMillis() /1000;
        demo04Producer.syncSend(id, "fail");
        log.info("发送消息编号{} 成功",id);
    }


}
