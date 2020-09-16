package com.xiaoliu.learn.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description: apache HttpClient4.5工具类
 * @author: liufb
 * @create: 2020/9/16 17:11
 **/
public class HttpClient4 {
    /**
     * 执行GET请求
     *
     * @param url 请求URL
     * @return 结果
     */
    public static String doGet(String url) {
        // 创建httpGet远程连接实例
        HttpGet httpGet = new HttpGet(url);
        // 设置请求头信息，鉴权
        httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
        // 设置配置请求参数
        RequestConfig requestConfig = RequestConfig.custom()
                // 连接主机服务超时时间
                .setConnectTimeout(35000)
                // 请求超时时间
                .setConnectionRequestTimeout(35000)
                // 数据读取超时时间
                .setSocketTimeout(60000)
                .build();
        // 为httpGet实例设置配置
        httpGet.setConfig(requestConfig);

        String result = "";
        try (// 通过址默认配置创建一个httpClient实例
             CloseableHttpClient httpClient = HttpClients.createDefault();
             // 执行get请求得到返回对象
             CloseableHttpResponse response = httpClient.execute(httpGet)
        ) {
            // 通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils中的toString方法将结果转换为字符串
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 执行POST请求
     *
     * @param url      请求URL
     * @param paramMap 请求参数
     * @return 返回值
     */
    public static String doPost(String url, Map<String, Object> paramMap) {
        String result = "";
        // 创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        // 配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom()
                // 设置连接主机服务超时时间
                .setConnectTimeout(35000)
                // 设置连接请求超时时间
                .setConnectionRequestTimeout(35000)
                // 设置读取数据连接超时时间
                .setSocketTimeout(60000)
                .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        // 封装post请求参数
        if (null != paramMap && paramMap.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // 通过map集成entrySet方法获取entity
            Set<Map.Entry<String, Object>> entrySet = paramMap.entrySet();
            // 循环遍历，获取迭代器
            for (Map.Entry<String, Object> mapEntry : entrySet) {
                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
            }

            // 为httpPost设置封装好的请求参数
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try (// 创建httpClient实例
             CloseableHttpClient httpClient = HttpClients.createDefault();
             // httpClient对象执行post请求,并返回响应参数对象
             CloseableHttpResponse httpResponse = httpClient.execute(httpPost)
        ) {
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
