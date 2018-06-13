package com.jzwzz.k8sdemo.k8sappupgrade.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class WebClientUtils {


    public static String getInfo(String url){

        CloseableHttpClient httpCilent2 = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(15000)   //设置连接超时时间
                .setConnectionRequestTimeout(15000) // 设置请求超时时间
                .setSocketTimeout(15000)
                .setRedirectsEnabled(true)//默认允许自动重定向
                .build();
        HttpGet httpGet2 = new HttpGet(url);
        httpGet2.setConfig(requestConfig);
        String strResult = "";
        try {
            HttpResponse httpResponse = httpCilent2.execute(httpGet2);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                strResult = EntityUtils.toString(httpResponse.getEntity());//获得返回的结果
                System.out.println(strResult);
            } else if (httpResponse.getStatusLine().getStatusCode() == 400) {
                System.out.println("error satus code:" + httpResponse.getStatusLine().getStatusCode());
                //..........
            } else if (httpResponse.getStatusLine().getStatusCode() == 500) {
                System.out.println("error satus code:" + httpResponse.getStatusLine().getStatusCode());
                //.............
            } else {
                System.out.println("error satus code:" + httpResponse.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpCilent2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return strResult;
    }
}
