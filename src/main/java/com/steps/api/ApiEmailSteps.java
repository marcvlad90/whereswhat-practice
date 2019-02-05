package com.steps.api;

import com.tools.constants.DateConstants;
import com.tools.constants.SerenityKeyConstants;
import com.tools.email.EmailProcessor;
import com.tools.email.GmailService;
import com.tools.entities.Booking;
import com.tools.utils.DateFormatter;
import com.tools.utils.DateUtils;
import com.tools.utils.SerenitySessionUtils;

import net.thucydides.core.annotations.Step;

import org.junit.Assert;

public class ApiEmailSteps extends AbstractApiSteps {
    private static final long serialVersionUID = 1L;

    @Step
    public void checkThatBookingRequestEmailNotificationWasReceived() {
        boolean isEmailFound = false;
        EmailProcessor emailProcessor = new EmailProcessor(new GmailService());
        Booking booking = SerenitySessionUtils.getFromSession(SerenityKeyConstants.BOOKING);
        String emailStartDateValue = DateFormatter.formatDate(DateUtils.parseStringIntoDate(booking.getStartDate(), DateConstants.WW_PATTERN),
                DateConstants.WW_EMAIL_NOTIFICATION_DATE_PATTERN);
        String emailEndDateValue = DateFormatter.formatDate(DateUtils.parseStringIntoDate(booking.getEndDate(), DateConstants.WW_PATTERN),
                DateConstants.WW_EMAIL_NOTIFICATION_DATE_PATTERN);
        for (int i = 0; i < 10; i++) {
            if (emailProcessor.getTheLastEmail().getSubject().contains("You have a new booking request for " + booking.getItem().getTitle())) {
                String emailContent = emailProcessor.getTheLastEmail().getMailContent();
                if (emailContent.contains("USER: " + booking.getUser().getName()) && emailContent.contains("ITEM: " + booking.getItem().getTitle())
                        && emailContent.contains("START DATE: " + emailStartDateValue) && emailContent.contains("END DATE: " + emailEndDateValue)) {
                    isEmailFound = true;
                    break;
                }
            }
        }
        Assert.assertTrue("The email request notification email was not received!", isEmailFound);

    }
}