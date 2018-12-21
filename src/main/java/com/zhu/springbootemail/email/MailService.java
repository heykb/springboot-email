package com.zhu.springbootemail.email;


public interface MailService {
    void sendSimpleEmail(String to, String subject,String content);
    void sendAttachmentsMail(String to, String subject,String content,String filepath);
    void sendvalicodeEmail(String to,String valicode);
}
