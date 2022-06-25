package com.example.demo.test;

public class TokenBucket {
    //当前令牌数
    private  int count;
    //最大令牌数
    private final int maxBucketCount = 100;
    // 每100ms 生成一个令牌
    private static  final int interval = 100;
    //上一次令牌生成的最后时间
    private long lastTime = System.currentTimeMillis();

    public void generateBucket(){
        long currentTime = System.currentTimeMillis();
        if(count == maxBucketCount){
            System.out.println("当前已达到最大令牌数，待消费完毕在添加");
            return ;
        }
        boolean isMoreThenInterval = (currentTime - lastTime) >= interval;
        if (isMoreThenInterval) {
            count += (currentTime - lastTime) /interval;
            System.out.println("当前最新的令牌数"+count);
            lastTime = currentTime;
            return ;
        }else {
            System.out.println("当前还未到达指定时间,过一会在来添加吧");
        }

    }

    public  void consumerBucket(){
        if (count <= 0) {
            System.out.println("当前令牌已使用完毕，等一会在来消费");
            return ;
        }
        count--;
        System.out.println("获取到令牌了,剩余令牌数"+ count);
        doBusiness();

    }
    public void  doBusiness(){
        System.out.println("开始执行业务逻辑了...");
    }

    public static void main(String[] args) {
        TokenBucket tokenBucket = new TokenBucket();
        //生产者以每100ms 速率产生一个bucket
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while(true){
                    tokenBucket.generateBucket();
                }
            }
        }).start();

        //消费者获取令牌执行业务逻辑
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tokenBucket.consumerBucket();
                }
            }
        }).start();
    }
}
