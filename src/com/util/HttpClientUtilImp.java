package com.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liu on 2017/5/11.
 */
public class HttpClientUtilImp implements HttpClientUtil{
    public  String send(String url,Map<String,String> data,String encoding){
        String respStr="";
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            List param=new ArrayList<NameValuePair>();
            if (data!=null&&!data.isEmpty()){
                for(Map.Entry<String,String> entry:data.entrySet()){
                    param.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
                }
            }
            UrlEncodedFormEntity reqEntity=new UrlEncodedFormEntity(param,encoding);
            httpPost.setEntity(reqEntity);
            HttpResponse response = client.execute(httpPost);
            HttpEntity respEntity = response.getEntity();
            respStr = EntityUtils.toString(respEntity);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respStr;
    }
    public  String send(String url, Map<String,String> data, Map<String,File> files, String encoding){
        String body="";
        CloseableHttpClient httpClient =null;
        try {
            httpClient = HttpClients.createDefault();
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(Charset.forName(encoding));
            HttpPost httpPost = new HttpPost(url);

            if (files!=null||!files.isEmpty()){
                for (Map.Entry<String,File> entry:files.entrySet()) {
                    File temp=entry.getValue();
                    if(temp!=null||temp.exists()){
                        builder.addBinaryBody("file",temp);
                    }
                }
            }
            if (data!=null&&!data.isEmpty()){
                for (Map.Entry<String,String> entry:data.entrySet()) {
                    builder.addTextBody(entry.getKey(),entry.getValue());
                }
            }
            HttpEntity reqEntity = builder.build();
            httpPost.setEntity(reqEntity);
            CloseableHttpResponse resp = httpClient.execute(httpPost);
            if (HttpStatus.SC_OK==resp.getStatusLine().getStatusCode()) {
                HttpEntity respEntity= resp.getEntity();
                if (reqEntity!=null){
                    body = EntityUtils.toString(reqEntity);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }
}
