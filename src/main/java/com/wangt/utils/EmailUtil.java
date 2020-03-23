package com.wangt.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.Random;

/**
 * @author wangt
 * @description 验证码
 * @date 2020/3/21
 */
public class EmailUtil {

    private  String codes = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
    private  Random r=new Random();
    private String code;//验证码
    private String email;//目标邮箱
    /**
     * 生成验证码
     */
    private  String createCode(){
        String code="";
        for(int i=0;i<4;i++){
            code=code+randomChar();
        }
        return code;
     }
    /**
     * 获取随机字符
     */
    private  char randomChar() {
        int index = r.nextInt(codes.length());
        return codes.charAt(index);
    }
    /*
     * 得到验证码
     */
    public String getCode(){
        return this.code;
    }
    /*
     * 得到目标邮箱
     */
    public String getEmail(){
        return this.email;
    }
    /*
     * 发送邮箱验证码
     */
    public String sendEmail(String email){
        //生成验证码
        this.code=createCode();
        try {
            //设置发件人
            String from = "3053738761@qq.com";
            //设置收件人
            String to = email;
            //设置邮件发送的服务器，这里为QQ邮件服务器
            String host = "smtp.qq.com";
            //获取系统属性
            Properties properties = System.getProperties();
            //SSL加密
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);

            //设置系统属性
            properties.setProperty("mail.smtp.host", host);
            properties.put("mail.smtp.auth", "true");

            //获取发送邮件会话、获取第三方登录授权码
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    //第三方登录授权码
                    return new PasswordAuthentication(from, "zvztmufynztbdgeh");
                }
            });
            Message message = new MimeMessage(session);
            //防止邮件被当然垃圾邮件处理，披上Outlook的马甲
            message.addHeader("X-Mailer", "Microsoft Outlook Express 6.00.2900.2869");
            //设置发件人
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //邮件主题
            message.setSubject("小标验证码");
            BodyPart bodyPart = new MimeBodyPart();
            //消息体（正文）
            bodyPart.setText(code);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);
            message.setContent(multipart);
            //发送邮件
            Transport.send(message);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        //存储目标邮箱
        this.email=email;
        return code;
    }
}
