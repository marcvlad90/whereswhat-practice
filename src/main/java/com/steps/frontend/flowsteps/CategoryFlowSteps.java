package com.steps.frontend.flowsteps;

import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

import com.steps.frontend.AbstractSteps;
import com.steps.frontend.CategorySteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ItemsSteps;
import com.tools.constants.Constants;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Category;
import com.tools.factories.CategoryFactory;
import com.tools.utils.SerenitySessionUtils;

public class CategoryFlowSteps extends AbstractSteps {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Steps
    private HeaderSteps headerSteps;
    @Steps
    private ItemsSteps itemsSteps;
    @Steps
    private CategorySteps categorySteps;

    @StepGroup
    public void uploadImageToCategory(String imageName) {
        Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.clickOnCategoryAction(category.getName(), Constants.ACTION_EDIT);
        categorySteps.uploadImageToCategory(Constants.TEST_DATA_FILE_PATH + imageName);
    }

    @StepGroup
    public void createCategory() {
        Category category = CategoryFactory.getCategoryInstance();
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.clickOnAction(Constants.ITEMS_PAGE_ACTION_ADD_CATEGORY);
        categorySteps.insertCategoryName(category.getName());
        categorySteps.clickAddCategoryButton();
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.CATEGORY, category);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.CATEGORIES, category);
    }

    @StepGroup
    public void renameCategory() {
        Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.clickOnCategoryAction(category.getName(), Constants.ACTION_EDIT);
        category.setName(category.getName() + " Updated");
        categorySteps.insertCategoryName(category.getName());
        categorySteps.clickUpdateCategoryButton();
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.CATEGORY, category);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.CATEGORIES, category);
    }

    @StepGroup
    public void deleteCategory() {
        Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.clickOnCategoryAction(category.getName(), Constants.ACTION_DELETE);
        itemsSteps.confirmDeletion();
    }
}
