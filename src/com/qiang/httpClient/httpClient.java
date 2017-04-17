package com.qiang.httpClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.qiang.Interface.httpClientInterface;

public class httpClient implements httpClientInterface {
	@Override
	public void ssl(String url,String file,String key) {
		CloseableHttpClient httpclient = null;  
        try {  
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());  
            FileInputStream instream = new FileInputStream(new File(file));  
            try {  
                // 加载keyStore     
                trustStore.load(instream, key.toCharArray());  
            } catch (CertificateException e) {  
                e.printStackTrace();  
            } finally {  
                try {  
                    instream.close();  
                } catch (Exception ignore) {  
                }  
            }  
            // 相信自己的CA和所有自签名的证书  
            SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();  
            // 只允许使用TLSv1协议  
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,  
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);  
            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();  
            // 创建http请求(get方式)  
            HttpGet httpget = new HttpGet(url);  
            System.out.println("executing request" + httpget.getRequestLine());  
            CloseableHttpResponse response = httpclient.execute(httpget);  
            try {  
                HttpEntity entity = response.getEntity();  
                System.out.println("----------------------------------------");  
                System.out.println(response.getStatusLine());  
                if (entity != null) {  
                    System.out.println("Response content length: " + entity.getContentLength());  
                    System.out.println(EntityUtils.toString(entity));  
                    EntityUtils.consume(entity);  
                }  
            } finally {  
                response.close();  
            }  
        } catch (ParseException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (KeyManagementException e) {  
            e.printStackTrace();  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (KeyStoreException e) {  
            e.printStackTrace();  
        } finally {  
            if (httpclient != null) {  
                try {  
                    httpclient.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
		

	@Override
	public void postForm(String url) {
		// 创建默认的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        // 创建httppost    
        HttpPost httppost = new HttpPost(url);  
        // 创建参数队列    
        List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();  
        formparams.add(new BasicNameValuePair("username", "admin"));  
        formparams.add(new BasicNameValuePair("password", "123456"));  
        UrlEncodedFormEntity uefEntity;  
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);  
            System.out.println("executing request " + httppost.getURI());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {  
                    System.out.println("--------------------------------------");  
                    System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));  
                    System.out.println("--------------------------------------");  
                }  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
		
	}

	@Override
	public String post(String url,List<BasicNameValuePair> formparams) {
		String responses = null ;
		
		// 创建默认的httpClient实例.    
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        
        // 创建httppost    
        HttpPost httppost = new HttpPost(url);  
        
        UrlEncodedFormEntity uefEntity;  
        try {  
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");  
            httppost.setEntity(uefEntity);  
            System.out.println("executing request " + httppost.getURI());  
            CloseableHttpResponse response = httpclient.execute(httppost);  

            try {  
                HttpEntity entity = response.getEntity();  
                responses = EntityUtils.toString(entity, "UTF-8");
                if (entity != null) {  
                    System.out.println("--------------------------------------");  
                    System.out.println("Response content: " + responses);  
                    System.out.println("--------------------------------------");  
                }  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
      
        return responses;
	}

	@Override
	public void get(String url) {
		 CloseableHttpClient httpclient = HttpClients.createDefault();  
		 
	        try {  
	            // 创建httpget.    
	            HttpGet httpget = new HttpGet(url);  
	            System.out.println("executing request " + httpget.getURI());  
	            // 执行get请求.    
	            CloseableHttpResponse response = httpclient.execute(httpget);  
	            Header[] header = response.getAllHeaders();
	            int l = header.length;
	            for(int x = 0;x <l ;x ++){
	            	if(header[x].getName().equals("Set-Cookie")){
	            		System.out.println(header[x].getValue());
	            		String[] COOKIES = header[x].getValue().split(";");
	            		for(int z = 0;z < COOKIES.length ;z ++){
	            			if(COOKIES[z].contains("IW_UUID_COOKIES")){
	            				System.out.println(COOKIES[z].substring(COOKIES[z].indexOf("=")+1, COOKIES[z].length()));
	            			}
	            		}
 	            		
	            	}
	            }
	            try {  
	                // 获取响应实体    
	                HttpEntity entity = response.getEntity();  
	                System.out.println("--------------------------------------");  
	                // 打印响应状态    
	                System.out.println(response.getStatusLine()); 
//	                if (entity != null) {  
//	                    // 打印响应内容长度    
//	                    System.out.println("Response content length: " + entity.getContentLength());  
//	                    // 打印响应内容    
//	                    System.out.println("Response content: " + EntityUtils.toString(entity,"utf-8")); 
//	                }  
//	                System.out.println("------------------------------------");  
	            } finally {  
	                response.close();  
	            }  
	        } catch (ClientProtocolException e) {  
	            e.printStackTrace();  
	        } catch (ParseException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        } finally {  
	            // 关闭连接,释放资源    
	            try {  
	                httpclient.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  
		
	}

	@Override
	public void uploadFile(String url,String file) {
		CloseableHttpClient httpclient = HttpClients.createDefault();  
        try {  
            HttpPost httppost = new HttpPost(url);  
  
            FileBody bin = new FileBody(new File(file));  
            StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);  
  
            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin).addPart("comment", comment).build();  
  
            httppost.setEntity(reqEntity);  
  
            System.out.println("executing request " + httppost.getRequestLine());  
            CloseableHttpResponse response = httpclient.execute(httppost);  
            try {  
                System.out.println("----------------------------------------");  
                System.out.println(response.getStatusLine());  
                HttpEntity resEntity = response.getEntity();  
                if (resEntity != null) {  
                    System.out.println("Response content length: " + resEntity.getContentLength());  
                }  
                EntityUtils.consume(resEntity);  
            } finally {  
                response.close();  
            }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
		
	}

	@Override
	public void downloadFile(String url, String destFileName) throws ClientProtocolException, IOException {
		// 生成一个httpclient对象
	    CloseableHttpClient httpclient = HttpClients.createDefault();
	    HttpGet httpget =new HttpGet(url);
	    HttpResponse response = httpclient.execute(httpget);
	    HttpEntity entity = response.getEntity();
	    InputStream in = entity.getContent();
	    File file =new File(destFileName);
	    try{
	        FileOutputStream fout =new FileOutputStream(file);
	        int l = -1;
	        byte[] tmp =new byte[1024];
	        while((l = in.read(tmp)) != -1) {
	            fout.write(tmp,0, l);
	            // 注意这里如果用OutputStream.write(buff)的话，图片会失真，大家可以试试
	        }
	        fout.flush();
	        fout.close();
	    }finally{
	        // 关闭低层流。
	        in.close();
	    }
	    httpclient.close();
	}

	public static void main(String[] args){
		
		
		
		String phone = "17412340000";
		httpClient hClient = new httpClient();
		
		hClient.get("http://pc.iwjwtest.com");
//		List<BasicNameValuePair> bList = new ArrayList<BasicNameValuePair>();
//		bList.add(new BasicNameValuePair("mobile", phone));
//		String token = hClient.post("http://pc.iwjwtest.com/getToken.action", bList);
//		
//		List<BasicNameValuePair> bList2 = new ArrayList<BasicNameValuePair>();
//		bList2.add(new BasicNameValuePair("mobile", phone));
//		bList2.add(new BasicNameValuePair("uuid", token));
//		bList2.add(new BasicNameValuePair("jiyanFlag", "1"));
//		bList2.add(new BasicNameValuePair("type", "0"));
//		System.out.println(hClient.post("http://pc.iwjwtest.com/sendCode.action", bList2));
//		
//		List<BasicNameValuePair> bList3 = new ArrayList<BasicNameValuePair>();
//		bList3.add(new BasicNameValuePair("mobile", phone));
//		
//		String code = null ;
//		Scanner scanner = new Scanner(System.in);
//		if(scanner.hasNextLine()){
//			code = scanner.nextLine();
//		}
//		
//		
//		bList3.add(new BasicNameValuePair("code", code));
//		bList3.add(new BasicNameValuePair("uuid", token));
//		System.err.println(hClient.post("http://pc.iwjwtest.com/login.action", bList3));;
	} 
}