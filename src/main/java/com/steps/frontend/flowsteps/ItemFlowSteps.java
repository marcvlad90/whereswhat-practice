package com.steps.frontend.flowsteps;

import com.steps.frontend.AbstractSteps;
import com.steps.frontend.HeaderSteps;
import com.steps.frontend.ItemSteps;
import com.steps.frontend.ItemsSteps;
import com.tools.constants.Constants;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Category;
import com.tools.entities.Item;
import com.tools.factories.ItemFactory;
import com.tools.utils.SerenitySessionUtils;

import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;

public class ItemFlowSteps extends AbstractSteps {
    private static final long serialVersionUID = 1L;

    @Steps
    private HeaderSteps headerSteps;
    @Steps
    private ItemsSteps itemsSteps;
    @Steps
    private ItemSteps itemSteps;

    @StepGroup
    public void uploadImageToItem(String imageName) {
        Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.searchForItem(item.getTitle());
        itemsSteps.clickOnItemAction(item.getTitle(), Constants.ACTION_EDIT);
        itemSteps.uploadImageToItem(Constants.TEST_DATA_FILE_PATH + imageName);
    }

    @StepGroup
    public void cloneItem(int numberOfClones) {
        Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.searchForItem(item.getTitle());
        itemsSteps.clickOnItemAction(item.getTitle(), Constants.ACTION_CLONE);
        itemsSteps.insertNumberOfClones(String.valueOf(numberOfClones));
        itemsSteps.clickOnCloneButton();
    }

    @StepGroup
    public void createItem() {
        Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        Item item = ItemFactory.getItemInstance();
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.clickOnAction(Constants.ITEMS_PAGE_ACTION_ADD_ITEM);
        itemSteps.insertItemName(item.getTitle());
        itemSteps.selectItemCategory(category.getName());
        itemSteps.clickAddItemButton();
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.ITEM, item);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.ITEMS, item);
    }

    @StepGroup
    public void moveItemInAnotherCategory() {
        Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.searchForItem(item.getTitle());
        itemsSteps.clickOnItemAction(item.getTitle(), Constants.ACTION_EDIT);
        itemSteps.selectItemCategory(category.getName());
        itemSteps.clickUpdateItemButton();
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.ITEM, item);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.ITEMS, item);
    }

    @StepGroup
    public void renameItem() {
        Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.searchForItem(item.getTitle());
        itemsSteps.clickOnItemAction(item.getTitle(), Constants.ACTION_EDIT);
        item.setTitle(item.getTitle() + " Updated");
        itemSteps.insertItemName(item.getTitle());
        itemSteps.clickUpdateItemButton();
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.ITEM, item);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.ITEMS, item);
    }

    @StepGroup
    public void deleteItem() {
        Item item = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        headerSteps.navigateToMenu(Constants.MENU_ITEM_ITEMS);
        itemsSteps.searchForItem(item.getTitle());
        itemsSteps.clickOnItemAction(item.getTitle(), Constants.ACTION_DELETE);
        itemsSteps.confirmDeletion();
    }
}
