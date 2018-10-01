package com.jzwzz.k8sdemo.k8sappupgrade;

import com.jzwzz.k8sdemo.k8sappupgrade.utils.WebClientUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ServiceClientLocalTest {


    String url = "http://192.168.56.21:30009/?delay=1";

    @Test
    public void test001() {


        AtomicInteger success = new AtomicInteger(0);
        AtomicInteger failure = new AtomicInteger(0);

        int count = 6;


        CountDownLatch latch = new CountDownLatch(count);
        List l = new ArrayList();

        for (int i = 0; i < count; i++) {
            System.out.println("i = " + i);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {

                        for (int j = 0; j < 240; j++) {

                            String result = WebClientUtils.getInfo(url);
                            System.out.println("result" + result);
//                            Assert.assertEquals("finished. 1", result);
                            if ("finished. 1".equals(result)) {
                                success.incrementAndGet();
                            } else {
                                failure.incrementAndGet();
                            }

                            output(success, failure);

                        }

                    } catch (RuntimeException e) {
                        failure.incrementAndGet();
                        throw e;
                    } finally {
                        latch.countDown();
                    }

                }
            });

            thread.start();

        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        output(success, failure);

    }

    public void output(AtomicInteger success, AtomicInteger failure) {

        System.out.println("========================>" + (success.get() + failure.get()) + " " + success + " / " + failure);
        System.out.println("========================>" + (success.get() + failure.get()) + " " + success + " / " + failure);
        System.out.println("========================>" + (success.get() + failure.get()) + " " + success + " / " + failure);
        System.out.println("========================>" + (success.get() + failure.get()) + " " + success + " / " + failure);

    }
}
