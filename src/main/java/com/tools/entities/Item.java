package com.tools.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Item {
    private Object[] customFields;
    private String title;
    private String itemCode;
    private int id;
    //    private Integer companyId;
    private Integer locationId;
    private String name;
    private String categoryTitle;
    private Category category;
    private int categoryId;
    private String retainedImage;

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemTag() {
        return itemCode;
    }

    @JsonProperty("item_code")
    public void setItemTag(String itemTag) {
        this.itemCode = itemTag;
    }

    @JsonProperty("Category")
    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    @JsonProperty("location_id")
    public Integer getLocationId() {
        return locationId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("custom_fields")
    public Object[] getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Object[] customFields) {
        this.customFields = customFields;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("item_code")
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @JsonProperty("category_id")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @JsonProperty("retained_image")
    public String getRetainedImage() {
        return retainedImage;
    }

    public void setRetainedImage(String retainedImage) {
        this.retainedImage = retainedImage;
    }

}
