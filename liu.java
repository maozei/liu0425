package com.meliora.manage120.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liu on 2017/4/24.
 */
public class HttpClientUtil {

        /**
         * ģ������
         *
         * @param url       ��Դ��ַ
         * @param map   �����б�
         * @param encoding  ����
         * @return
         * @throws ParseException
         * @throws IOException
         */
        public static String send(String url, Map<String,String> map, String encoding) throws ParseException, IOException {
            String body = "";

            //����httpclient����
            CloseableHttpClient client = HttpClients.createDefault();
            //����post��ʽ�������
            HttpPost httpPost = new HttpPost(url);

            //װ�����
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            if(map!=null){
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
            //���ò��������������
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

            //����header��Ϣ
            //ָ������ͷ��Content-type������User-Agent��
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            //ִ��������������õ������ͬ��������
            CloseableHttpResponse response = client.execute(httpPost);
            //��ȡ���ʵ��
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //��ָ������ת�����ʵ��ΪString����
                body = EntityUtils.toString(entity, encoding);
            }
            EntityUtils.consume(entity);
            //�ͷ�����
            response.close();
            return body;
        }
}
