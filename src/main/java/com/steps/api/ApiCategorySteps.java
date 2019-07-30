package com.steps.api;

import com.tools.constants.ApiUrlConstants;
import com.tools.constants.Constants;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.CategoriesCollection;
import com.tools.entities.Category;
import com.tools.factories.CategoryFactory;
import com.tools.utils.InstanceUtils;
import com.tools.utils.SerenitySessionUtils;

import net.bytebuddy.utility.RandomString;
import net.thucydides.core.annotations.Step;

import org.junit.Assert;

import java.util.List;

public class ApiCategorySteps extends AbstractApiSteps {
    private static final long serialVersionUID = 1L;

    @Step
    public void checkThatCategoryExists(int categoryId, String categoryName) {
        Category categoryResponse = getResource(ApiUrlConstants.CATEGORIES + "/" + categoryId, Category.class);
        Assert.assertTrue(String.format("Category %s does not exist! ", categoryName), (categoryResponse.getId() == categoryId)
                && categoryResponse.getName().contentEquals(categoryName));
    }

    @Step
    public void checkThatCategoryExists() {
        Category categoryRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        checkThatCategoryExists(categoryRequest.getId(), categoryRequest.getName());
    }

    @Step
    public void checkThatCategoriesExist() {
        List<Category> categoryRequests = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORIES);
        for (Category categoryRequest : categoryRequests) {
            checkThatCategoryExists(categoryRequest.getId(), categoryRequest.getName());
        }
    }

    @Step
    public void createCategory() {
        Category categoryRequest = CategoryFactory.getCategoryInstance();
        Category categoryResponse = createResource(ApiUrlConstants.CATEGORIES, categoryRequest, Category.class);
        categoryRequest = (Category)InstanceUtils.mergeObjects(categoryRequest, categoryResponse);
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.CATEGORY, categoryRequest);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.CATEGORIES, categoryRequest);
    }

    @Step
    public void createSupervisionedCategory() {
        Category categoryRequest = CategoryFactory.getSupervisionedCategoryInstance();
        Category categoryResponse = createResource(ApiUrlConstants.CATEGORIES, categoryRequest, Category.class);
        categoryRequest = (Category)InstanceUtils.mergeObjects(categoryRequest, categoryResponse);
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.CATEGORY, categoryRequest);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.CATEGORIES, categoryRequest);
    }

    @Step
    public void changeDefaultBookingLengthDaysValue(int numberOfDays) {
        Category categoryRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        categoryRequest.setDefaultBookingLength(numberOfDays * 24);
        updateResource(ApiUrlConstants.UPDATE_CATEGORY,
                categoryRequest, categoryRequest.getId());
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.CATEGORY, categoryRequest);
    }

    @Step
    public void changeDefaultBookingLengthHoursValue(int numberOfHours) {
        Category categoryRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        categoryRequest.setDefaultBookingLength(numberOfHours);
        updateResource(ApiUrlConstants.UPDATE_CATEGORY,
                categoryRequest, categoryRequest.getId());
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.CATEGORY, categoryRequest);
    }

    @Step
    public void changeMaxBookingLengthDaysValue(int numberOfDays) {
        Category categoryRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        categoryRequest.setMaxBookingLength(numberOfDays * 24);
        updateResource(ApiUrlConstants.UPDATE_CATEGORY,
                categoryRequest, categoryRequest.getId());
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.CATEGORY, categoryRequest);
    }

    @Step
    public void changeMaxBookingLengthHoursValue(int numberOfHours) {
        Category categoryRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        categoryRequest.setMaxBookingLength(numberOfHours);
        updateResource(ApiUrlConstants.UPDATE_CATEGORY,
                categoryRequest, categoryRequest.getId());
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.CATEGORY, categoryRequest);
    }

    @Step
    public void deleteCategory() {
        Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        deleteResource(ApiUrlConstants.CATEGORIES, category.getId());
    }

    @Step
    public void renameCategory() {
        Category categoryRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        categoryRequest.setName(categoryRequest.getName() + RandomString.make(10));
        updateResource(ApiUrlConstants.UPDATE_CATEGORY,
                categoryRequest, categoryRequest.getId());
    }

    @Step
    public int getTheNumberOfCategories() {
        return getResource(ApiUrlConstants.CATEGORIES + "?perPage=9999", CategoriesCollection[].class).length;
    }

    @Step
    public void deleteAllCategories() {
        CategoriesCollection[] categories = getResource(ApiUrlConstants.CATEGORIES + "?perPage=9999", CategoriesCollection[].class);
        for (int i = 0; i < categories.length; i++) {
            try {
                deleteResourceWithoutAssertion(ApiUrlConstants.CATEGORIES, categories[i].getId());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Step
    public void uploadImageToCategory(String fileName) {
        Category categoryRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        uploadResourceToEntity(ApiUrlConstants.UPLOAD_IMAGE_FILE, Constants.TEST_DATA_FILE_PATH + fileName, SerenityKeyConstants.CATEGORY,
                categoryRequest.getId());
    }

    @Step
    public void uploadImageToCategoriesFromSession(String fileName) {
        List<Category> categories = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORIES);
        for (Category category : categories) {
            uploadResourceToEntity(ApiUrlConstants.UPLOAD_IMAGE_FILE, Constants.TEST_DATA_FILE_PATH + fileName, SerenityKeyConstants.CATEGORY,
                    category.getId());
        }

    }

    @Step
    public void deleteAllCategoriesFromSession() {
        try {
            List<Category> categories = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORIES);
            for (Category category : categories) {
                deleteResourceWithoutAssertion(ApiUrlConstants.CATEGORIES, category.getId());
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }
}
