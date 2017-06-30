package com.qiang.mail;

/**  
* 发送邮件需要使用的基本信息  
*/   
import java.util.Properties;   
public class MailSenderInfo {   
      
    // 发送邮件的服务器的IP和端口   
    private String mailServerHost;   
    private String mailServerPort = "465";   
      
    // 邮件发送者的地址   
    private String fromAddress;   
      
    // 邮件接收者的地址   
    private String toAddress;   
      
    // 登陆邮件发送服务器的用户名和密码   
    private String userName;   
    private String password;   
      
    // 是否需要身份验证   
    private boolean validate = true;   
      
    // 邮件主题   
    private String subject;   
      
    // 邮件的文本内容   
    private String content;   
      
    // 邮件附件的文件名   
    private String[] attachFileNames;   
 // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
    //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
    //     打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
    /*
    // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
    //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
    //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
     * 
    
    final String smtpPort = "465";
    props.setProperty("mail.smtp.port", smtpPort);
    props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.setProperty("mail.smtp.socketFactory.fallback", "false");
    props.setProperty("mail.smtp.socketFactory.port", smtpPort);
     */
    private String socketFactory = "javax.net.ssl.SSLSocketFactory";
    
    private String socketFactoryport = "465";
    
    private boolean socketFactoryFallback = false;
    
    private String protocol = "smtp";
    
    public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getSocketFactoryport() {
		return socketFactoryport;
	}
	public void setSocketFactoryport(String socketFactoryport) {
		this.socketFactoryport = socketFactoryport;
	}
	public String getSocketFactory() {
		return socketFactory;
	}
	public void setSocketFactory(String socketFactory) {
		this.socketFactory = socketFactory;
	}
	public boolean isSocketFactoryFallback() {
		return socketFactoryFallback;
	}
	public void setSocketFactoryFallback(boolean socketFactoryFallback) {
		this.socketFactoryFallback = socketFactoryFallback;
	}
    
    /**  
      * 获得邮件会话属性  
      */   
    public Properties getProperties(){   
      Properties p = new Properties();   
      p.put("mail.smtp.host", this.mailServerHost);   
      p.put("mail.smtp.port", this.mailServerPort);   
      p.put("mail.smtp.auth", validate ? "true" : "false");  
      p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      p.put("mail.smtp.socketFactory.fallback", this.socketFactoryFallback);
      p.put("mail.smtp.socketFactory.port", this.socketFactoryport);
      p.put("mail.transport.protocol", this.protocol);
      return p;   
    }   
    public String getMailServerHost() {   
      return mailServerHost;   
    }   
    public void setMailServerHost(String mailServerHost) {   
      this.mailServerHost = mailServerHost;   
    }  
    public String getMailServerPort() {   
      return mailServerPort;   
    }  
    public void setMailServerPort(String mailServerPort) {   
      this.mailServerPort = mailServerPort;   
    }  
    public boolean isValidate() {   
      return validate;   
    }  
    public void setValidate(boolean validate) {   
      this.validate = validate;   
    }  
    public String[] getAttachFileNames() {   
      return attachFileNames;   
    }  
    public void setAttachFileNames(String[] fileNames) {   
      this.attachFileNames = fileNames;   
    }  
    public String getFromAddress() {   
      return fromAddress;   
    }   
    public void setFromAddress(String fromAddress) {   
      this.fromAddress = fromAddress;   
    }  
    public String getPassword() {   
      return password;   
    }  
    public void setPassword(String password) {   
      this.password = password;   
    }  
    public String getToAddress() {   
      return toAddress;   
    }   
    public void setToAddress(String toAddress) {   
      this.toAddress = toAddress;   
    }   
    public String getUserName() {   
      return userName;   
    }  
    public void setUserName(String userName) {   
      this.userName = userName;   
    }  
    public String getSubject() {   
      return subject;   
    }  
    public void setSubject(String subject) {   
      this.subject = subject;   
    }  
    public String getContent() {   
      return content;   
    }  
    public void setContent(String textContent) {   
      this.content = textContent;   
    }   
}  