package com.zc.smartcity.redis.util;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
public class HttpUtil {
    /**
     * POST请求（同步）
     * 连接超时时间：1秒
     * 传输超时时间：3秒
     * @param url 请求地址
     * @param params 请求参数
     * @return 响应结果
     */
    public final static String doPostFormSync(String url,Map<String,String> params) {
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(1000).setSocketTimeout(3000).build();
        httpPost.setConfig(requestConfig);
        List<NameValuePair> nvps = Lists.newArrayList();
        Set<String> keys = params.keySet();
        for(String k : keys){
            nvps.add(new BasicNameValuePair(k,params.get(k)));
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        return null;
    }

    /**
     * POST请求（同步）
     * 连接超时时间：1秒
     * 传输超时时间：3秒
     * @param url 请求地址
     * @param entity 请求参数
     * @return 响应结果
     */
    public final static String doPostSync(String url,String entity) {
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(1000).setSocketTimeout(3000).build();
        httpPost.setConfig(requestConfig);

        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            httpPost.setEntity(new StringEntity(entity));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        return null;
    }

    /**
     * POST请求（异步）
     * 连接超时时间：1秒
     * 传输超时时间：3秒
     * @param url 请求地址
     * @param params 请求参数
     * @return 响应结果
     */
    public final static String doPostFormAsyn(String url,Map<String,String> params) {
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(1000).setSocketTimeout(3000).build();
        httpPost.setConfig(requestConfig);
        List<NameValuePair> nvps = Lists.newArrayList();
        Set<String> keys = params.keySet();
        for(String k : keys){
            nvps.add(new BasicNameValuePair(k,params.get(k)));
        }
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        httpclient.start();
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            Future<HttpResponse> future = httpclient.execute(httpPost, null);
            HttpResponse response = future.get(3000,TimeUnit.MILLISECONDS);// 获取结果
            return EntityUtils.toString(response.getEntity());
        } catch (InterruptedException e) {
            e.printStackTrace();
             log.info(e.getMessage());
        } catch (ExecutionException e) {
            e.printStackTrace();
             log.info(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
             log.info(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
             log.info(e.getMessage());
        } catch (TimeoutException e) {
            e.printStackTrace();
             log.info(e.getMessage());
        }
        return null;
    }

    /**
     * POST请求（异步）
     * 连接超时时间：1秒
     * 传输超时时间：3秒
     * @param url 请求地址
     * @param entity 请求参数
     * @return 响应结果
     */
    public final static String doPostAsyn(String url,String entity) {
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(1000).setSocketTimeout(3000).build();
        httpPost.setConfig(requestConfig);

        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        httpclient.start();
        try {
            httpPost.setEntity(new StringEntity(entity));
            Future<HttpResponse> future = httpclient.execute(httpPost, null);
            HttpResponse response = future.get(3000,TimeUnit.MILLISECONDS);// 获取结果
            return EntityUtils.toString(response.getEntity());
        } catch (InterruptedException e) {
            e.printStackTrace();
             log.info(e.getMessage());
        } catch (ExecutionException e) {
            e.printStackTrace();
             log.info(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
             log.info(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
             log.info(e.getMessage());
        } catch (TimeoutException e) {
            e.printStackTrace();
             log.info(e.getMessage());
        }
        return null;
    }
}
