package com.tcl.demo.boot.common.test.learn.day2;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ObjectWaitNotifyCase {

    public static void main(String[] args) {

        StorageCenter storageCenter = new StorageCenter();

        // 构建2个生产线程
        Producer producer1 = new Producer(storageCenter);
        Producer producer2 = new Producer(storageCenter);

        Consumer consumer1 = new Consumer(storageCenter, 5);

        producer1.start();
        //producer2.start();

        try {

            consumer1.start();

        } catch (Exception e) {

        }

    }
}


/**
 * 生产者
 */
class Producer extends Thread {

    private StorageCenter storageCenter;

    public Producer(StorageCenter storageCenter) {
        this.storageCenter = storageCenter;
    }

    @Override
    public void run() {

        while (true) {
            try {

                Thread.sleep(1_000);

                List<String> add = Lists.newArrayList();

                for (int idx = 0; idx < 2; idx++) {

                    add.add(System.nanoTime() + "");
                }

                storageCenter.producerProcess(add);

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    }
}

/**
 * 消费者
 */
class Consumer extends Thread {

    private StorageCenter storageCenter;

    private int num;

    public Consumer(StorageCenter storageCenter, int num) {
        this.storageCenter = storageCenter;
        this.num = num;
    }

    @Override
    public void run() {

        while (true) {

            try {
                List<String> result = storageCenter.consumerProcess(num);

                System.out.println("Consumer结果：" + JSON.toJSONString(result));

                Thread.sleep(1_000);
            } catch (Exception e) {

                e.printStackTrace();
            }

        }

    }
}

/**
 * 存储中心
 */
class StorageCenter {

    private Object lock = new Object();

    private List<String> contextPool = new ArrayList<>();

    private final Integer MAX_LIMIT = 10;

    public List<String> consumerProcess(int consumerCnt) {

        long curTime = System.currentTimeMillis();

        Thread curThread = Thread.currentThread();

        System.out.println("消费：当前线程[" + curThread.getName() + "consumerProcess:1:" + curTime);

        if (consumerCnt <= 0) {

            // System.out.println("consumerProcess:" + curTime);
            // System.out.println("消费：当前线程[" + curThread.getName() + "]消费数量非法，直接返回空结果");
            return Lists.newArrayList();
        }

        synchronized (lock) {

            System.out.println("消费：当前线程[" + curThread.getName() + "consumerProcess:2:" + curTime);

            // System.out.println("消费：当前线程[" + curThread.getName() + "]，数量" + contextPool.size());

            while (consumerCnt > contextPool.size()) {

                try {

                    System.out.println("消费：当前线程[" + curThread.getName() + "consumerProcess:3:" + curTime);

                    // System.out.println("消费：当前线程[" + curThread.getName() + "]消费消息[" + consumerCnt + "]个，当前容器个数[" + contextPool.size() + "],无法获取，进行主动等待");

                    lock.wait();

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

            List<String> result = new ArrayList<>(consumerCnt);

            System.out.println("消费：当前线程[" + curThread.getName() + "consumerProcess:4:" + curTime + "集合：" + contextPool);

            for (int idx = 0; idx < consumerCnt; idx++) {
                try{
                    result.add(contextPool.remove(idx));
                }catch (Exception e){

                    System.out.println("消费：当前线程[" + curThread.getName() + "consumerProcess:5:" + curTime + "异常：" + e.toString());
                    //e.printStackTrace();
                }
            }


            System.out.println("消费：当前线程[" + curThread.getName() + "consumerProcess:6:" + curTime + "结果：" + result);
            System.out.println("消费：当前线程[" + curThread.getName() + "consumerProcess:7:" + curTime + "剩余：" + contextPool);


            lock.notifyAll();

            return result;
        }
    }

    public boolean producerProcess(List<String> contextList) {

        Thread curThread = Thread.currentThread();

        if (CollectionUtils.isEmpty(contextList)) {

            // System.out.println("生产：当前线程[" + curThread.getName() + "]生产消息非法，直接返回false");

            return false;
        }

        synchronized (lock) {

            int addSize = contextList.size();

            while (contextPool.size() + addSize > MAX_LIMIT) {

                try {

                    // System.out.println("生产：当前线程[" + curThread.getName() + "]生产消息[" + addSize + "]个，当前容器个数[" + contextPool.size() + "],上限个数[" + MAX_LIMIT + "]，进行主动等待");

                    lock.wait();

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }


            for (String context : contextList) {

                contextPool.add(context);
            }

            lock.notifyAll();
            // System.out.println("生产：当前线程[" + curThread.getName() + "]生产消息[" + addSize + "]个，当前容器个数[" + contextPool.size() + "],放入完成,内容：" + contextPool);
        }

        return true;
    }

}





