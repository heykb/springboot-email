package com.zhu.springbootemail.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;
    @Value("${mail.fromMail.addr}")
    private String from;


    @Override
    public void sendSimpleEmail(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);

        mailSender.send(simpleMailMessage);
    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filepath) {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            /**
             *   MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");使用true代表复杂信息
             */
            helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            //使用true位html内容
            helper.setText(content,true);

            //携带附件
            FileSystemResource file = new FileSystemResource(new File(filepath));
            String fileName = file.getFilename();
            System.out.println(fileName);
            helper.addAttachment(fileName,file);
            //内联静态资源（图片）
            FileSystemResource img = new FileSystemResource(new File("C:\\Users\\LY\\Desktop\\springboot-email\\src\\main\\resources\\static\\3.jpg"));
            helper.addInline("3",img);
            mailSender.send(message);

            /*测试
            mailService.sendAttachmentsMail("xxxxxx2@qq.com","简单邮件","<html><body>这是有图片的邮件：<img src='cid:3' ></body></html>","C:\\Users\\..");
             */
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendvalicodeEmail(String to, String valicode) {

        Context context = new Context();
        context.setVariable("valicode",valicode);
        String valicodeEmail = templateEngine.process("emailTemplate",context);
        System.out.println(valicodeEmail);
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject("邮箱验证");
            mimeMessageHelper.setText(valicodeEmail,true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
