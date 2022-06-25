package com.example.demo.test;

import com.example.demo.SpringbootRabbitmqDemo1Application;
import com.example.demo.producer.Demo08Producer;
import com.example.demo.producer.Demo09Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootRabbitmqDemo1Application.class)
@Slf4j
public class Demo09ProducerTest {

    @Autowired
    private Demo09Producer demo09Producer;


    /**
     * 2
     */
    @Test
    public  void testSendMsg() throws  Exception{
        for (int i = 0; i <3;i++){
            int id = (int) System.currentTimeMillis()/1000;
            demo09Producer.sendMsg(id);
            Thread.sleep(1000);
        }
    }

}
