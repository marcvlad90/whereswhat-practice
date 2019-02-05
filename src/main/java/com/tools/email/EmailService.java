package com.tools.email;

import com.tools.entities.Mail;

import java.util.List;

public interface EmailService {
    public Mail getTheLastEmail();

    public List<Mail> getTheLastInboxEmails(int numberOfEmails);

    public Mail getTheLastEmailWithSubject(String subject);

    public Mail getTheLastEmailWithSender(String sender);

    public Mail getTheLastEmailHavingTextInContent(String text);
}
