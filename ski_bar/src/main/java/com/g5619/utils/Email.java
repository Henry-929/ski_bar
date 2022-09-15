package com.g5619.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class Email {
    public static void sendEmail(){
        JavaMailSender javaMailSender = new JavaMailSenderImpl();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("这是一个邮件");
        message.setSubject("测试");
        message.setTo("1179603260@qq.com");
        javaMailSender.send(message);
    }
}
