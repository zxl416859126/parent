package com.example.demo.test;

import com.example.demo.SpringbootRabbitmqDemo1Application;
import com.example.demo.producer.Demo05Producer;
import com.example.demo.producer.Demo06Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootRabbitmqDemo1Application.class)
@Slf4j
public class Demo06ProducerTest {

    @Autowired
    private Demo06Producer demo06Producer;



    public void testSyncSend(int n) {
        for (int i = 0;i < n;i++){
            demo06Producer.syncSend(i);
            log.info("发送消息编号{} 成功",i);
        }
    }

    @Test
    public  void testSyncSend01(){
        testSyncSend(3);
    }


    @Test
    public  void testSyncSend02(){
        testSyncSend(20);
    }

}
