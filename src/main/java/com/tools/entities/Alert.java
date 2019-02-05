package com.tools.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Alert {
    private Notification[] notificationsCollection;
    private EmailReceiveOption[] emailReceiveOptionsCollection;

    @JsonProperty("email_receive_options")
    public EmailReceiveOption[] getEmailReceiveOptionsCollection() {
        return emailReceiveOptionsCollection;
    }

    public void setEmailReceiveOptionsCollection(EmailReceiveOption[] emailReceiveOptionsCollection) {
        this.emailReceiveOptionsCollection = emailReceiveOptionsCollection;
    }

    @JsonProperty("notifications")
    public Notification[] getNotificationsCollection() {
        return notificationsCollection;
    }

    public void setNotificationsCollection(Notification[] notificationsCollection) {
        this.notificationsCollection = notificationsCollection;
    }
}
