package com.tools.factories;

import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Alert;
import com.tools.entities.EmailReceiveOption;
import com.tools.entities.Notification;
import com.tools.entities.User;
import com.tools.utils.SerenitySessionUtils;

public class AlertFactory {

    public static Alert getIndividualBookingRequestAlertInstance() {
        User user = SerenitySessionUtils.getFromSession(SerenityKeyConstants.USER);
        Alert alert = new Alert();
        Notification[] notifications = new Notification[1];
        Notification notification = new Notification();
        notification.setUserId(user.getId());
        notification.setEventId("ManagedItemBooked");
        notification.setEnabled(true);
        notifications[0] = notification;
        EmailReceiveOption[] emailReceiveOptions = new EmailReceiveOption[1];
        EmailReceiveOption emailIndividualNotificationReceiveOption = new EmailReceiveOption();
        emailIndividualNotificationReceiveOption.setUserId(user.getId());
        emailIndividualNotificationReceiveOption.setEventId("IndividualNotification");
        emailIndividualNotificationReceiveOption.setEnabled(true);
        emailReceiveOptions[0] = emailIndividualNotificationReceiveOption;
        alert.setNotificationsCollection(notifications);
        alert.setEmailReceiveOptionsCollection(emailReceiveOptions);
        return alert;
    }
}
