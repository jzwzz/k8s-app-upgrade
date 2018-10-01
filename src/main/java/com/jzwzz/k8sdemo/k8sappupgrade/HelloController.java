package com.jzwzz.k8sdemo.k8sappupgrade;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class HelloController {

    @Value("${app.version}")
    private String version;

    private AtomicInteger count = new AtomicInteger(0);

    /**
     * delay n second
     *
     * @param delay
     * @return
     */
    @RequestMapping("/")
    @ResponseBody
    public String index(@RequestParam(name = "delay", defaultValue = "0") int delay) {
        System.out.println(">>>index.count = " + count.incrementAndGet());

        for (int i = 0; i < delay; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(version + ", current step: " + (i + 1) + "/" + delay);
        }

        String result = "version:" + version + ", finished. delay:" + delay + ", count:" + count.decrementAndGet();
        System.out.println(result);

        return result;

    }


}
