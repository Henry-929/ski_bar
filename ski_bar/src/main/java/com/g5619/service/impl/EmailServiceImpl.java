package com.g5619.service.impl;

import com.g5619.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
@Service
public class EmailServiceImpl implements EmailService {
        @Resource
        JavaMailSender javaMailSender;

    public void contextLoads(String email){
            SimpleMailMessage message=new SimpleMailMessage();
            message.setText("活动审批已通过");
            message.setSubject("滑雪俱乐部活动通知");
            message.setTo(email);
            message.setFrom("1295184078@qq.com");  // 发件方
            javaMailSender.send(message);
        }

    @Override
    public void joinin(String email) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setText("参加活动成功");
        message.setSubject("滑雪俱乐部活动通知");
        message.setTo(email);
        message.setFrom("1295184078@qq.com");  // 发件方
        javaMailSender.send(message);

    }
}
