package com.qiang.mail;

import com.qiang.utils.UseProperties;

/** 
	 密码验证器 
  */  
public class SendMail{  
	public static void main(String[] args){  
//		//这个类主要是设置邮件  
//	      MailSenderInfo mailInfo = new MailSenderInfo();   
//	      mailInfo.setMailServerHost("smtp.qq.com");   
//	      mailInfo.setMailServerPort("465");   
//	      mailInfo.setValidate(true);   
//	      mailInfo.setUserName("993965670@qq.com");   
//	      mailInfo.setPassword("xmvlbvrbsnghbfgg");//您的邮箱密码   
//	      mailInfo.setFromAddress("993965670@qq.com");   
//	      mailInfo.setToAddress("646818878@qq.com");   
//	      mailInfo.setSubject("设置邮箱标题");   
//	      mailInfo.setContent("设置邮箱内容");   
//		//这个类主要来发送邮件  
//	      SimpleMailSender sms = new SimpleMailSender();  
//		 sms.sendTextMail(mailInfo);//发送文体格式   
////		 sms.sendHtmlMail(mailInfo);//发送html格式  
		System.err.println(new SendMail().toSendMail("测试标题", "测试发送邮箱", "txt"));
	    }
	/**
	 * 
	 * @param mailTitle  邮件标题
	 * @param mailBody   邮件内容
	 * @param form     邮件格式 txt或者html
	 * @return
	 */
	public String toSendMail(String mailTitle ,String mailBody,String form){
		MailSenderInfo mailInfo = new MailSenderInfo();
		  mailInfo.setMailServerHost(UseProperties.GetValueByKey("mail.properties", "mail.smtp.host"));   
	      mailInfo.setMailServerPort(UseProperties.GetValueByKey("mail.properties", "mail.smtp.port"));
	      mailInfo.setSocketFactoryport(UseProperties.GetValueByKey("mail.properties", "mail.smtp.socketFactory.port"));
	      mailInfo.setValidate(Boolean.parseBoolean(UseProperties.GetValueByKey("mail.properties", "mail.smtp.auth")));  
	      mailInfo.setSocketFactoryFallback(Boolean.parseBoolean(UseProperties.GetValueByKey("mail.properties", "mail.smtp.socketFactory.fallback")));
	      mailInfo.setUserName(UseProperties.GetValueByKey("mail.properties", "mail.smtp.from"));   
	      mailInfo.setPassword(UseProperties.GetValueByKey("mail.properties", "mail.smtp.password"));
	      mailInfo.setFromAddress(UseProperties.GetValueByKey("mail.properties", "mail.smtp.from"));   
	      mailInfo.setToAddress(UseProperties.GetValueByKey("mail.properties", "mail.smtp.toAddress"));
	      mailInfo.setProtocol(UseProperties.GetValueByKey("mail.properties", "mail.transport.protocol"));
	      mailInfo.setSubject(mailTitle);   
	      mailInfo.setContent(mailBody);   
		//这个类主要来发送邮件  
	      SimpleMailSender sms = new SimpleMailSender();  
	      boolean isSuccess;
	      if (form.equals("txt")) {
	    	//发送文体格式 
	    	  isSuccess = sms.sendTextMail(mailInfo);
	    	  if (isSuccess) {
	    		  return "send mail success";
			}else {
				return "send mail failed";
			}
	    	  
		}else if (form.equals("html")) {
			//发送html格式 
			isSuccess = sms.sendHtmlMail(mailInfo);
			if (isSuccess) {
	    		  return "send mail success";
			}else {
				return "send mail failed";
			}
		}else {
			isSuccess = false;
			return "send mail failed";
		}
		   
	}
}  