package com.tools.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {
    private String name;
    private int id;
    private int companyId;
    private int defaultBookingLength;
    private int maxBookingLength;
    private boolean needsApproval;

    @JsonProperty("needs_approval")
    public boolean isNeedsApproval() {
        return needsApproval;
    }

    public void setNeedsApproval(boolean needsApproval) {
        this.needsApproval = needsApproval;
    }

    @JsonProperty("max_booking_length")
    public int getMaxBookingLength() {
        return maxBookingLength;
    }

    public void setMaxBookingLength(int maxBookingLength) {
        this.maxBookingLength = maxBookingLength;
    }

    @JsonProperty("default_booking_length")
    public int getDefaultBookingLength() {
        return defaultBookingLength;
    }

    public void setDefaultBookingLength(int defaultBooking_length) {
        this.defaultBookingLength = defaultBooking_length;
    }

    @JsonProperty("company_id")
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
