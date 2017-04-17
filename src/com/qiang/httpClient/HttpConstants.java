package com.qiang.httpClient;

import java.util.HashMap;
import java.util.Map;

public class HttpConstants {

    public static int connectTimeout = 3000;

    public static int socketTimeout = 10000;
    
    public static int connectionRequestTimeout = 10000;
    
    // 最大连接数
    public static int MaxTotal = 200;
    // 设置最大路由
    public static int DefaultMaxPerRoute = 2;
    
    public static void setDefaultMaxPerRoute(int defaultMaxPerRoute) {
		DefaultMaxPerRoute = defaultMaxPerRoute;
	}

	public static void setMaxTotal(int maxTotal) {
		MaxTotal = maxTotal;
	}

	public static void setConnectionRequestTimeout(int connectionRequestTimeout) {
		HttpConstants.connectionRequestTimeout = connectionRequestTimeout;
	}

	public static Map<String, Object> commonHeaders = new HashMap<String, Object>();


    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public void setCommonHeader(String key, Object object) {
        this.commonHeaders.put(key, object);
    }

}

