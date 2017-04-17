package com.qiang.Interface;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

public interface httpClientInterface {
	 /** 
     * HttpClient连接SSL 
     */  
    public void ssl(String url,String file,String key) ;
    
    /** 
     * post方式提交表单（模拟用户登录请求） 
     */  
    public void postForm(String url);
    
    /** 
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果 
     */  
    public String post(String url,List<BasicNameValuePair> formparams);
    
    /** 
     * 发送 get请求 
     */  
    public void get(String url) ;
    
    /** 
     * 上传文件 
     */  
    public void uploadFile(String url,String file) ;
    
    /**
     * 下载文件
     */
    public void downloadFile(String url, String destFileName) throws ClientProtocolException, IOException ;
}
