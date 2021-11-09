package com.example.musicdiary2.mail;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;
import java.io.File;
import java.io.UnsupportedEncodingException;

public class MailHandler {

    private JavaMailSender mailSender;
    private MimeMessage message;
    private MimeMessageHelper messageHelper;

    // 생성자
    public MailHandler(JavaMailSender mailSender)
            throws MessagingException {
        this.mailSender = mailSender;
        message = this.mailSender.createMimeMessage();
        messageHelper = new MimeMessageHelper(message, true, "UTF-8");
    }

    // 보내는 사람 이메일
    public void setTo(String email) throws MessagingException {
        messageHelper.setTo(email);
    }

    // 받는 사람 이메일
    public void setFrom(String email) throws MessagingException {
        messageHelper.setFrom(email);
    }

    // 받는 사람 이메일
//    public void setFrom(String email, String name)
//            throws UnsupportedEncodingException, MessagingException {
//        messageHelper.setFrom(email, name);
//    }

    // 메일 제목
    public void setSubject(String subject) throws MessagingException {
        messageHelper.setSubject(subject);
    }

    // 메일 내용
    public void setText(String text, boolean useHtml) throws MessagingException {
        messageHelper.setText(text, useHtml);
    }

    public void send() {
//        try {
            mailSender.send(message);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    // 첨부 파일
//    public void setAttach(String displayFileName, String pathToAttachment) throws MessagingException, IOException {
//        File file = new ClassPathResource(pathToAttachment).getFile();
//        FileSystemResource fsr = new FileSystemResource(file);
//
//        messageHelper.addAttachment(displayFileName, fsr);
//    }

    // 이미지 삽입
//    public void setInline(String contentId, String pathToInline) throws MessagingException, IOException {
//        File file = new ClassPathResource(pathToInline).getFile();
//        FileSystemResource fsr = new FileSystemResource(file);
//
//        messageHelper.addInline(contentId, fsr);
//    }

    // 메일 내용
//    public void setText(String htmlContent) throws MessagingException {
//        messageHelper.setText(htmlContent, true);
//    }

//    public void addInline(String contentId, DataSource dataSource)
//            throws MessagingException {
//        messageHelper.addInline(contentId, dataSource);
//    }



}
