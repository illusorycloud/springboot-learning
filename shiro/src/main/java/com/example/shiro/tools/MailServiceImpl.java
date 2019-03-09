package com.example.shiro.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class MailServiceImpl implements MailService {
    @Autowired
    private MailSender mailSender;
    private String from = "cqkctandroidt@163.com"; //和配置文件username一样 加了后缀

    @Override
    public void sendMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from); //发件人
        message.setTo(to); //收件人
        message.setSubject(subject); //邮件标题
        message.setText(content); //邮件内容
        try {
            mailSender.send(message);
            System.out.println("简单邮件已经发送。");
        } catch (Exception e) {
            System.out.println("发送简单邮件时发生异常！" + e.getMessage());

        }
    }
}
