package com.util;

import java.io.File;
import java.util.Map;

/**
 * Created by liu on 2017/5/12.
 */
public interface HttpClientUtil {
   abstract String send(String url, Map<String, String> data, String encoding);
   abstract String send(String url, Map<String, String> data, Map<String, File> files, String encoding);
}
