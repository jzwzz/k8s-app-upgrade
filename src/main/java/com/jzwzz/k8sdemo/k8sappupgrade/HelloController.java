package com.jzwzz.k8sdemo.k8sappupgrade;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${app.version}")
    private String version;

    /**
     * delay n second
     *
     * @param delay
     * @return
     */
    @RequestMapping("/")
    @ResponseBody
    public String index(@RequestParam(name = "delay", defaultValue = "0") int delay) {

        for (int i = 0; i < delay; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(version + ", current step: " + (i + 1) + "/" + delay);
        }

        String result = version + ", finished. " + delay;
        System.out.println(version + ", delay = " + delay);

        return result;

    }


}
