package com.tools.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category {
    private String name;
    @JsonInclude(Include.NON_DEFAULT)
    private int id;
    @JsonInclude(Include.NON_DEFAULT)
    private int companyId;
    @JsonInclude(Include.NON_DEFAULT)
    private int defaultBookingLength;
    @JsonInclude(Include.NON_DEFAULT)
    private int maxBookingLength;
    @JsonInclude(Include.NON_DEFAULT)
    private boolean needsApproval;
    private CustomField[] categoryCustomFields;

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
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("custom_fields")
    public CustomField[] getCategoryCustomFields() {
        return categoryCustomFields;
    }

    public void setCategoryCustomFields(CustomField[] categoryCustomFields) {
        this.categoryCustomFields = categoryCustomFields;
    }
}
