package com.sm.cn.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Auther: tjp
 * @Date: 2020/10/23 21:31
 * @Description:
 */
@Component
@PropertySource(value={"classpath:email.properties"})
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
@Value("${email.from}")
    private String from;
  public  void sendMail(String to ,String message){
      MimeMessage mimeMessage=javaMailSender.createMimeMessage();
      MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,"utf-8");
      try {
          helper.setFrom(from);
          helper.setTo(to);
          helper.setSubject("新员工信息");
          helper.setText( message,true);
      javaMailSender.send(mimeMessage);
      } catch (MessagingException e) {
          e.printStackTrace();
      }




  }

}