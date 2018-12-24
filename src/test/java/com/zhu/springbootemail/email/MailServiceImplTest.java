package com.zhu.springbootemail.email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testSimpleMail(){
        mailService.sendSimpleEmail("ssss@qq.com","简单邮件","你好啊a");
    }
    @Test
    public void sendAttachmentsMail() {
        mailService.sendAttachmentsMail("sss@qq.com","简单邮件","<html><body>这是有图片的邮件：<img src='cid:3' ></body></html>","C:\\Users\\LY\\Desktop\\homestay-system.zip");
    }
    @Test
    public void testValicodeMail()
    {
        mailService.sendvalicodeEmail("ssss@qq.com","f54fd1");
    }

}