package com.qiang.httpClient;

import com.leo.common.util.MD5Digest;
import com.manyi.json.FasterJsonTool;
import com.qiang.utils.AppEncrypt;
import com.qiang.utils.CommonUtils;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

@SuppressWarnings({ "deprecation", "unused" })
public class HttpPostClient extends HttpClientUtil{
/**
 * 
 * @param jsonRequestBody
 * @param os
 * @return
 */
	public String paraMd5(Map<String, Object> jsonRequestBody ,String OS){
    	List<Map.Entry<String, Object>> params = new ArrayList<Map.Entry<String, Object>>(jsonRequestBody.entrySet());// 
     	Collections.sort(params, new Comparator<Map.Entry<String, Object>>() {
 			@Override
				public int compare(Entry<String, Object> o1,Entry<String, Object> o2) {
 				if(o1.getKey()==null || o2.getKey()==null)
 					return 0;
 				return o1.getKey().compareTo(o2.getKey());
 			}
 		});
     	
         //a=1&b=b&MT0VT5EN1FAP7SGA840OBW2DUFJUAB&1100000000
         String secret = "";
         for(Map.Entry<String, Object> param : params){
             String key = param.getKey();
             String value = "";
             Object value1 =  param.getValue();
             if(value1.getClass().isArray()){
             	String[] ta = (String[])param.getValue();
             	if(ta!=null&&ta.length>0){
             		for(int i=0;i<ta.length;i++){
             			if(i==0){
             				value = "["+ta[0];
             			}
             			else{
             				value = value+", "+ta[i];
             			}
             			
             			if(i==(ta.length-1)){
             				value += "]";
             			}
             		}
             	}
             }
             else{
             	value=value1.toString();
             }
             secret = secret + key + "=" + value + "&";
         }
			if(secret.endsWith("&"))
             secret = secret.substring(0, secret.length()-1);
         
         
         long currentTime = System.currentTimeMillis()/1000000;
         String secret_;
         if(OS.contains("iphone")){
        	secret_ = secret + "&" + "KX3RFJ6YB6FNR1DQAHGLYPWSEW0OQF" + "&" + currentTime ;
         }else{
        	 secret_ = secret + "&" + "3Q7EZLF6235A832GKEU5N42YM0EQOF" + "&" + currentTime ;
         }
         
         return  MD5Digest.getMD5Digest(secret_);
    }
    
    public String getTime(){
    	return new Date().getTime() + "";
    }
    
    public static void main(String[] args) {

      HttpPostClient httpPostClient = new HttpPostClient();
      httpPostClient.setHeader("cityId", "2");
      httpPostClient.setHeader("App-Key", "iwjw.superjia.com");
      httpPostClient.setHeader("App_Time", new Date().getTime() + "");
      httpPostClient.setHeader("ver", "6.2");
      httpPostClient.setHeader("os", "iphone OS");
      httpPostClient.setHeader("imei", "85F6664F-6FF3-42DF-888C-B0D280501165");
      Map<String, Object> pars = new HashMap<String, Object>();
		pars.put("mobile", "17412341256");
		pars.put("type", "0");
		httpPostClient.setHeader("App-Secret", httpPostClient.paraMd5(pars,"iphone OS"));
		
     String string = sendHttpPostJson("http://userapp.iwjwtest.com/ihouse/user/sendVerifyCode.rest", pars);
      System.out.println(string);
    	
    }
}
