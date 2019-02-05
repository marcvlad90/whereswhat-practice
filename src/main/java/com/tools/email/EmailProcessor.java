package com.tools.email;

import com.tools.entities.Mail;

import java.util.List;

public class EmailProcessor {
    private EmailService emailService;

    public EmailProcessor(EmailService emailService) {
        this.emailService = emailService;
    }

    public Mail getTheLastEmail() {
        return this.emailService.getTheLastEmail();
    }

    public List<Mail> getTheLastInboxEmails(int numberOfEmails) {
        return this.emailService.getTheLastInboxEmails(numberOfEmails);
    }

    public Mail getTheLastEmailWithSubject(String subject) {
        return this.emailService.getTheLastEmailWithSubject(subject);
    }

    public Mail getTheLastEmailWithSender(String sender) {
        return this.emailService.getTheLastEmailWithSender(sender);
    }

    public Mail getTheLastEmailHavingTextInContent(String text) {
        return this.emailService.getTheLastEmailHavingTextInContent(text);
    }
}
