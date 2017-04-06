package util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {

	public static void sendMail(String to,String code) throws AddressException, MessagingException{
		//连接邮箱
		Properties props=new Properties();
		 props.setProperty("mail.debug", "true");  
	        // 发送服务器需要身份验证  
	        props.setProperty("mail.smtp.auth", "true");  
	        // 设置邮件服务器主机名  
	        props.setProperty("mail.host", "smtp.163.com");  
	        // 发送邮件协议名称  
	        props.setProperty("mail.transport.protocol", "smtp");  
		Session session=Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("loveolcc@163.com", "wshxj961012");
			}
		
		});
		//创建邮件对象
		Message message=new MimeMessage(session);
		//设置发信人
		message.setFrom(new InternetAddress("loveolcc@163.com"));
		//设置收信人
		message.setRecipient(RecipientType.TO, new InternetAddress(to));
		//设置主题
		message.setSubject("激活账号");
		//设置正文内容
		message.setContent("<h1>账号激活</h1><h3>请点击以下连接激活</h3><p><a href='http://10.10.22.42/javamail/ActiveServlet?code="+code+"'>http://10.10.22.42/javamail/ActiveServlet?code="+code+"'</a></p>", "text/html;charset=UTF-8");
		//发送邮件
		Transport.send(message);
	}
}
