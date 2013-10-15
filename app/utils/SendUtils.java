package utils;

import java.net.MalformedURLException;
import java.net.URL;

import models.SAttachment;
import models.SEmail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import play.Logger;



/**
 * 发送工具类
 * 1.发送邮件
 * 2.发送短信息
 * 3.发送站内信
 * @author zhangpeng
 *
 */
public class SendUtils {
	
	/**
	 * 发送邮件(单封)
	 * @param email
	 * @param subject
	 * @param content
	 * @throws EmailException 
	 */
	public static void mail(SEmail semail){
		
		try {
			Email email = new SimpleEmail();
			email.setHostName(AppConfig.Email_Host);
			email.setSmtpPort(AppConfig.Email_Port);
			email.setAuthenticator(new DefaultAuthenticator(
					AppConfig.Email_Username, AppConfig.Email_Password));
			email.setSSLOnConnect(true);
			email.setFrom(AppConfig.Email_Email, AppConfig.Eamil_ShowName);
			email.setSubject(semail.subject);
			email.setCharset("utf-8");
			email.setMsg(semail.content);
			email.addTo(semail.email);
			email.send();
		} catch (EmailException e) {
			Logger.info("Send Mail Exception =>" + e.getMessage());
		}


	}
	
	
	/**
	 * 发送电子邮件带附件(单封)
	 * @param email
	 * @param subject
	 * @param content
	 * @throws EmailException 
	 */
	public static void mailWithAttachment(SEmail semail,SAttachment att) throws EmailException{

		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName(AppConfig.Email_Host);
		email.addTo(semail.email);
		email.setFrom(AppConfig.Email_Email,AppConfig.Eamil_ShowName);
		email.setSubject(semail.subject);
		email.setMsg(semail.content);

		// add the attachment
		email.attach(att.attachment,att.attachmentName,att.attachmentDescription);

		// send the email
		email.send();

	}
	
	/**
	 * 发送电子邮件带网络附件(单封)
	 * @param email
	 * @param subject
	 * @param content
	 * @throws EmailException 
	 * @throws MalformedURLException 
	 */
	public static void mailWithURLAttachment(SEmail semail,SAttachment att) throws EmailException, MalformedURLException{
		// Create the attachment
		EmailAttachment attachment = new EmailAttachment();
		attachment.setURL(new URL(att.urlattachment));
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription(att.attachmentDescription);
		attachment.setName(att.attachmentName);

		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName(AppConfig.Email_Host);
		email.addTo(semail.email);
		email.setFrom(AppConfig.Email_Email,AppConfig.Eamil_ShowName);
		email.setSubject(semail.subject);
		email.setMsg(semail.content);

		// add the attachment
		email.attach(attachment);

		// send the email
		email.send();

	}
	
	/**
	 * 发送电子邮件HTML格式(单封)
	 * @param uemail
	 * @param subject
	 * @param htmlMsg
	 * @param textMsg
	 * @throws EmailException
	 */
	public static void mailHtml(String uemail, String subject, String htmlMsg, String textMsg) throws EmailException{
		HtmlEmail email = new HtmlEmail();
		email.setHostName(AppConfig.Email_Host);
		email.setSmtpPort(AppConfig.Email_Port);
		email.setAuthenticator(new DefaultAuthenticator(AppConfig.Email_Username, AppConfig.Email_Password));
		email.setSSLOnConnect(true);
		email.addTo(uemail);
		email.setFrom(AppConfig.Email_Email, AppConfig.Eamil_ShowName);
		email.setCharset("utf-8");
		email.setSubject(subject);
		email.setHtmlMsg(htmlMsg);
		email.setTextMsg(textMsg);
		email.send();
	}
	
	
}
