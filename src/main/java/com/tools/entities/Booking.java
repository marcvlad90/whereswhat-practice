package com.tools.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import org.junit.Ignore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tools.constants.DateConstants;
import com.tools.utils.DateUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Booking {
    private String startDate;
    private String endDate;
    private String endDatePending;
    private String returnDate;
    @JsonInclude(Include.NON_DEFAULT)
    private int itemId;
    @JsonInclude(Include.NON_DEFAULT)
    private int userId;
    private String clientTime;
    @JsonInclude(Include.NON_DEFAULT)
    private int id;
    private Item item;
    private User user;
    private String status;
    private boolean returnItem;
    private boolean canReturn;
    private String note;
    private long fullDaysBookingNumber;
    private String extensionStatus;
    private String endDateInitial;

    @Ignore
    public String getEndDateInitial() {
        return endDateInitial;
    }

    public void setEndDateInitial(String endDateInitial) {
        this.endDateInitial = endDateInitial;
    }

    @JsonProperty("extension_status")
    public String getExtensionStatus() {
        return extensionStatus;
    }

    public void setExtensionStatus(String extensionStatus) {
        this.extensionStatus = extensionStatus;
    }

    public long getFullDaysBookingNumber() {
        System.out.println("The expected number of full days booking is: " + fullDaysBookingNumber);
        return fullDaysBookingNumber;
    }

    public void setBookingFullDaysNumber() {
        LocalDateTime startDateDateType;
        try {
            startDateDateType = DateUtils.parseStringIntoDate((getStartDate()), DateConstants.WW_PATTERN);
        } catch (DateTimeParseException e) {
            startDateDateType = DateUtils.parseStringIntoDate((getStartDate()), DateConstants.WW_RETURN_DATE_PATTERN);
        }
        LocalDateTime endDateDateType = DateUtils.parseStringIntoDate((getEndDate()), DateConstants.WW_PATTERN);
        System.out.println("START " + startDateDateType.plusMinutes(60 - startDateDateType.getMinute()).plusHours(23 - startDateDateType.getHour()));
        System.out.println("END " + endDateDateType);
        this.fullDaysBookingNumber = startDateDateType.until(endDateDateType, ChronoUnit.DAYS);
    }

    @JsonProperty("note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @JsonProperty("client_time")
    public String getClientTime() {
        return clientTime;
    }

    @JsonProperty("client_time")
    public void setClientTime(String clientTime) {
        this.clientTime = clientTime;
    }

    @JsonProperty("end_date_pending")
    public String getEndDatePending() {
        return endDatePending;
    }

    @JsonProperty("end_date_pending")
    public void setEndDatePending(String endDatePending) {
        this.endDatePending = endDatePending;
    }

    @JsonProperty("return_date")
    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @JsonProperty("can_return")
    public boolean isCanReturn() {
        return canReturn;
    }

    public void setCanReturn(boolean canReturn) {
        this.canReturn = canReturn;
    }

    @JsonProperty("return_item")
    public boolean isReturnItem() {
        return returnItem;
    }

    public void setReturnItem(boolean returnItem) {
        this.returnItem = returnItem;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("item")
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("start_date")
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("end_date")
    public String getEndDate() {
        return endDate;
    }

    @JsonProperty("end_date")
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("item_id")
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @JsonProperty("user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
