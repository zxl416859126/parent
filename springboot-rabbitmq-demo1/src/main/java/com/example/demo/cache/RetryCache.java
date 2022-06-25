package com.example.demo.cache;

import com.example.demo.common.Constants;
import com.example.demo.common.DetailResponse;
import com.example.demo.common.MessageSender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Data
public class RetryCache {
    private boolean stop = false;
    private Map<Integer, MessageWithTime> map = new ConcurrentHashMap<>();

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class MessageWithTime {
        long time;
        Object message;
    }

    public void add(int id, Object message) {
        map.put(id, new MessageWithTime(System.currentTimeMillis(), message));
    }

    public void del(Integer id) {
        map.remove(id);
    }

//    public void startRetry() {
//        new Thread(() ->{
//            while (!stop) {
//                try {
//                    Thread.sleep(Constants.RETRY_TIME_INTERVAL);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                long now = System.currentTimeMillis();
//                for (Integer key : map.keySet()) {
//                    MessageWithTime messageWithTime = map.get(key);
//                    if (null != messageWithTime) {
//                        if (messageWithTime.getTime() + 3 * Constants.VALID_TIME < now) {
//                            log.info("send message failed after 3 min " + messageWithTime);
//                            del(key);
//                        } else if (messageWithTime.getTime() + Constants.VALID_TIME < now) {
////                            DetailResponse detailRes = sender.send(messageWithTime.getMessage());
////                            if (detailRes.isSuccess()) {
////                                del(key);
////                            }
//                        }
//                    }
//                }
//            }
//        }).start();
//    }
}
