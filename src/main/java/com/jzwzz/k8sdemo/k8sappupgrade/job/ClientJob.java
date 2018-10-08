package com.jzwzz.k8sdemo.k8sappupgrade.job;

import ch.sbb.esta.openshift.gracefullshutdown.GracefulShutdownHealthCheck;
import com.jzwzz.k8sdemo.k8sappupgradeclient.utils.WebClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ClientJob {

    @Autowired
    private GracefulShutdownHealthCheck healthIndicator;

    @Scheduled(cron = "*/1 * * * * ? ")
    public void execute() {

        System.out.println(">>> ClientJob.execute()" + healthIndicator);

        if (!this.healthIndicator.health().getStatus().equals(Status.UP)) {
            System.out.println(">>> ClientJob.execute() not health, don't execute job.");
            return;
        }

        callService();

        System.out.println("<<< ClientJob.execute()");


    }

    public void callService() {
        int iThreadCount = 2;
        int iRequestCountPerThread = 2;
        int delay = 20;

        String url = "http://k8s-app-upgrade:8200/?delay=" + delay;

        runThread(url, iThreadCount, iRequestCountPerThread);

    }

    private void runThread(String url, int iThreadCount, int iRequestCountPerThread) {
        AtomicInteger success = new AtomicInteger(0);
        AtomicInteger failure = new AtomicInteger(0);


        CountDownLatch latch = new CountDownLatch(iThreadCount);
        List l = new ArrayList();

        for (int i = 0; i < iThreadCount; i++) {
            System.out.println("i = " + i);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {

                        for (int j = 0; j < iRequestCountPerThread; j++) {

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
