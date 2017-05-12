package com.app;

import com.util.HttpClientUtilImp;

import java.io.File;
import java.util.HashMap;

/**
 * Created by liu on 2017/5/12.
 */
public class TestApp {
   public static void main( String agrs[]){
       HttpClientUtilImp client = new HttpClientUtilImp();
       HashMap<String, String> data = new HashMap<>();
       data.put("deathPic.patientId","9628");
       HashMap<String, File> files = new HashMap<>();
       File file = new File("C:\\Users\\liu\\Desktop\\TEMP\\捕获.PNG");
       files.put("file",file);
       String resp = client.send("http://localhost:8080/fileUpload!deathPicUp.action", data, files, "UTF-8");
       System.out.println(resp);
   }
}
