//package com.jzwzz.k8sdemo.k8sappupgrade;
//
//import com.jzwzz.k8sdemo.k8sappupgrade.utils.WebClientUtils;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class ServiceClientTest {
//
//
//    @Test
//    public void test001Local() {
//
//        int iThreadCount = 1;
//        int iRequestCountPerThread = 1;
//        int delay = 1;
//
//        String url = "http://localhost:8200/?delay=" + delay;
//
//        runTHread(url, iThreadCount, iRequestCountPerThread);
//
//    }
//
//    @Test
//    public void test002Local() {
//
//        int iThreadCount = 1;
//        int iRequestCountPerThread = 1;
//        int delay = 40;
//
//        String url = "http://localhost:8200/?delay=" + delay;
//
//        runTHread(url, iThreadCount, iRequestCountPerThread);
//
//    }
//
//    @Test
//    public void test001Remote() {
//        int iThreadCount = 6;
//        int iRequestCountPerThread = 240;
//        int delay = 1;
//
//        String url = "http://192.168.56.21:30009/?delay=" + delay;
//
//        runTHread(url, iThreadCount, iRequestCountPerThread);
//
//    }
//
//
//    private void runTHread(String url, int iThreadCount, int iRequestCountPerThread) {
//        AtomicInteger success = new AtomicInteger(0);
//        AtomicInteger failure = new AtomicInteger(0);
//
//
//        CountDownLatch latch = new CountDownLatch(iThreadCount);
//        List l = new ArrayList();
//
//        for (int i = 0; i < iThreadCount; i++) {
//            System.out.println("i = " + i);
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//
//                    try {
//
//                        for (int j = 0; j < iRequestCountPerThread; j++) {
//
//                            String result = WebClientUtils.getInfo(url);
//                            System.out.println("result" + result);
////                            Assert.assertEquals("finished. 1", result);
//                            if ("finished. 1".equals(result)) {
//                                success.incrementAndGet();
//                            } else {
//                                failure.incrementAndGet();
//                            }
//
//                            output(success, failure);
//
//                        }
//
//                    } catch (RuntimeException e) {
//                        failure.incrementAndGet();
//                        throw e;
//                    } finally {
//                        latch.countDown();
//                    }
//
//                }
//            });
//
//            thread.start();
//
//        }
//
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        output(success, failure);
//    }
//
//    public void output(AtomicInteger success, AtomicInteger failure) {
//
//        System.out.println("========================>" + (success.get() + failure.get()) + " " + success + " / " + failure);
//        System.out.println("========================>" + (success.get() + failure.get()) + " " + success + " / " + failure);
//        System.out.println("========================>" + (success.get() + failure.get()) + " " + success + " / " + failure);
//        System.out.println("========================>" + (success.get() + failure.get()) + " " + success + " / " + failure);
//
//    }
//
//}
