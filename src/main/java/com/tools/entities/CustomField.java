package com.tools.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomField {
    private String name;
    @JsonInclude(Include.NON_DEFAULT)
    private int id;
    private String value;
    @JsonInclude(Include.NON_DEFAULT)
    private int customFieldId;
    private String customFieldName;

    @JsonProperty("custom_field_name")
    public String getCustomFieldName() {
        return customFieldName;
    }

    public void setCustomFieldName(String customFieldName) {
        this.customFieldName = customFieldName;
    }

    @JsonProperty("custom_field_id")
    public Integer getCustomFieldId() {
        return customFieldId;
    }

    public void setCustomFieldId(int customFieldId) {
        this.customFieldId = customFieldId;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
