package com.hjy.shop.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by admin on 2017/3/8.
 */
public class MailUtil {
    public static void sendMail(String to,String code){
        /**
         * 1.获取Session
         * 2.创建一个邮箱对象messgage
         * 3.发送邮箱Transportz
         */
        Properties props= new Properties();
//        props.setProperty("mail.transport.protocol","smtp");
//        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.host","localhost");
        Session session=Session.getInstance(props,new Authenticator(){

            protected PasswordAuthentication getPasswordAuthenticator(){
                return new PasswordAuthentication("10086@hjy.com","123456");
            }
        });
        Message message= new MimeMessage(session);
        //设置发件人
        try {
            message.setFrom(new InternetAddress("10086@hjy.com"));
            //设置收件人
            message.addRecipient(MimeMessage.RecipientType.TO,new InternetAddress(to));
            //CC抄送 BCC密送
            //设置标题
            message.setSubject("From shopping online");
            message.setContent("<h1>请点击下面的链接来激活您的账户!!</h1><h3><a href='http://192.168.149.1:8080/shop/user_active.action?code="+code+"'>http://192.168.149.1:8080/shop/user_active.action?code="+code+"</a></h3>",
                    "text/html;charset=utf-8");
            //发送邮件
//            session.getTransport().connect("smtp.163.com","h786459419y@163.com","hjy19960921");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
