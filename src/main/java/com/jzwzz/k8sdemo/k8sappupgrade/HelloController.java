package com.jzwzz.k8sdemo.k8sappupgrade;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {


    @RequestMapping("/")
    @ResponseBody
    public String index(@RequestParam(name = "delay") int delay) {

        String version = "v1.0";
        for (int i = 0; i < delay; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(version + ", current step: " + (i + 1) + "/" + delay);
        }

        String result = "finished. " + delay;
        System.out.println(version + ", delay = " + delay);

        return result;

    }


    @RequestMapping("/outOfMemory")
    @ResponseBody
    public String outOfMemory() {

        List list = new ArrayList();

        long length = 10000000 * 1000000;

        for (int i = 0; i < length; i++) {
            list.add(i + "dump111111111111111111111111111111111111111");
            list.add(i + "dump333333333333333333333333333333331");
            list.add(i + "dump333333333333333333333333333333332");
            list.add(i + "dump333333333333333333333333333333333");
            list.add(i + "dump333333333333333333333333333333334");
            list.add(i + "dump333333333333333333333333333333335");
            list.add(i + "dump333333333333333333333333333333336");
            list.add(i + "dump333333333333333333333333333333337");
            list.add(i + "dump333333333333333333333333333333338");
            list.add(i + "dump333333333333333333333333333333339");

            if((i % 10000) == 0){
                Map result = getMemoryInfo();
                System.out.println(result.get("freeMemory") + "/" + result.get("totalMemory")  + "/" + result.get("maxMemory") );
            }
        }


        String result = "finished. ";

        return result;

    }


    public Map getMemoryInfo(){
        Map result = new HashMap();

        Runtime runtime = Runtime.getRuntime();

        //空闲内存
        long freeMemory = runtime.freeMemory();
        result.put("freeMemory", byteToM(freeMemory));

        //内存总量
        long totalMemory = runtime.totalMemory();
        result.put("totalMemory", byteToM(totalMemory));

        //最大允许使用的内存
        long maxMemory = runtime.maxMemory();
        result.put("maxMemory", byteToM(maxMemory));

        return result;
    }

    @RequestMapping("/showMemory")
    @ResponseBody
    public Map showMemory() {



        return getMemoryInfo();

    }

    /**
     * 把byte转换成M
     *
     * @param bytes
     * @return
     */
    public static long byteToM(long bytes) {
        long kb = (bytes / 1024 / 1024);
        return kb;
    }

}
