package com.steps.api;

import com.jayway.restassured.path.json.JsonPath;
import com.tools.constants.ApiUrlConstants;
import com.tools.constants.Constants;
import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Category;
import com.tools.entities.Item;
import com.tools.entities.ItemsCollection;
import com.tools.entities.Location;
import com.tools.factories.ItemFactory;
import com.tools.utils.CSVWriters;
import com.tools.utils.InstanceUtils;
import com.tools.utils.SerenitySessionUtils;

import net.bytebuddy.utility.RandomString;
import net.thucydides.core.annotations.Step;

import org.junit.Assert;
import org.junit.experimental.categories.Categories;

import java.io.File;
import java.util.List;

public class ApiItemSteps extends AbstractApiSteps {
    private static final long serialVersionUID = 1L;

    @Step
    public void deleteFile(String filePath) {
        File file = new File(filePath);
        file.delete();
    }

    @Step
    public void createMultipleItemsFromCsvTemplate(List<Item> items) throws Exception {
        String fileName = RandomString.make(10) + ".csv";
        AbstractApiSteps.extraHeaders.put("Entity-Type", "item");
        AbstractApiSteps.extraHeaders.put("Content-Type", "multipart/form-data");
        CSVWriters.writeInCsv(items, Constants.DOWNLOAD_FILE_PATH + fileName);

        String fileNameOnDisk = uploadCSVResource(ApiUrlConstants.CSV_UPLOAD, Constants.DOWNLOAD_FILE_PATH, fileName);
        JsonPath jsonPath = new JsonPath(fileNameOnDisk);
        String fileNameProcessed = jsonPath.get("file");

        createItemFromCSV(ApiUrlConstants.PROCESS_CSV_FILE + "?filename=" + fileNameProcessed + "&" + "category_id=" + null);

        for (Item item : items) {
            List<Item> itemsResponse = getResources(ApiUrlConstants.ITEMS + "?title_or_code=" + item.getTitle() + "&category_id=" + item.getCategoryId(),
                    Item.class);
            item = (Item)InstanceUtils.mergeObjects(item, itemsResponse.get(0));
            SerenitySessionUtils.putOnSession(SerenityKeyConstants.ITEM, item);
            SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.ITEMS, item);
        }
        AbstractApiSteps.extraHeaders.remove("Entity-Type");
        AbstractApiSteps.extraHeaders.remove("Content-Type");
        deleteFile(Constants.DOWNLOAD_FILE_PATH + fileName);
    }

    @Step
    public void createMultipleItemsFromDownloadedCsvTemplate(List<Item> items, boolean areMultipleCategories) throws Exception {
        String fileName;
        Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        List<Categories> categories = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORIES);
        if (categories.size() == 1) {
            fileName = category.getName() + ".csv";
        }
        else {
            fileName = "Multiple Categories.csv";
        }
        AbstractApiSteps.extraHeaders.put("Entity-Type", "item");
        AbstractApiSteps.extraHeaders.put("Content-Type", "multipart/form-data");
        System.out.println("Append file path is; " + Constants.DOWNLOAD_FILE_PATH + fileName);
        CSVWriters.appendInCsv(items, Constants.DOWNLOAD_FILE_PATH + fileName, areMultipleCategories);

        String fileNameOnDisk = uploadCSVResource(ApiUrlConstants.CSV_UPLOAD, Constants.DOWNLOAD_FILE_PATH, fileName);
        JsonPath jsonPath = new JsonPath(fileNameOnDisk);
        String fileNameProcessed = jsonPath.get("file");
        if (categories.size() == 1) {
            createItemFromCSV(ApiUrlConstants.PROCESS_CSV_FILE + "?filename=" + fileNameProcessed + "&" + "category_id=" + category.getId());
        } else {
            createItemFromCSV(ApiUrlConstants.PROCESS_CSV_FILE + "?filename=" + fileNameProcessed + "&" + "category_id=" + null);
        }

        for (Item item : items) {
            List<Item> itemsResponse = getResources(ApiUrlConstants.ITEMS + "?title_or_code=" + item.getTitle() + "&category_id=" + item.getCategoryId(),
                    Item.class);
            item = (Item)InstanceUtils.mergeObjects(item, itemsResponse.get(0));
            SerenitySessionUtils.putOnSession(SerenityKeyConstants.ITEM, item);
            SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.ITEMS, item);
        }
        AbstractApiSteps.extraHeaders.remove("Entity-Type");
        AbstractApiSteps.extraHeaders.remove("Content-Type");
        deleteFile(Constants.DOWNLOAD_FILE_PATH + fileName);
    }

    @Step
    public void checkThatItemExists() {
        boolean isFound = false;
        Item itemRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        ItemsCollection[] items = getResource(ApiUrlConstants.ITEMS + "?per_page=9999&category_id=" + category.getId(),
                ItemsCollection[].class);
        for (int i = 0; i < items.length; i++) {
            if (items[i].getId() == itemRequest.getId()) {
                if (items[i].getTitle().contentEquals(itemRequest.getTitle())) {
                    if (items[i].getCategory().getName().contentEquals(category.getName())) {
                        isFound = true;
                        break;
                    }
                }
            }
        }
        Assert.assertTrue(String.format("The item %s does not exist under %s category!", itemRequest.getTitle(), category.getName()), isFound);
    }

    @Step
    public void checkThatItemsExist() {
        List<Category> categories = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORIES);
        List<Item> items = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEMS);
        for (Category category : categories) {
            ItemsCollection[] itemsCollection = getResource(ApiUrlConstants.ITEMS + "?per_page=9999&category_id=" + category.getId(),
                    ItemsCollection[].class);
            boolean isItemFound = false;
            for (Item item : items) {
                for (int i = 0; i < itemsCollection.length; i++) {
                    if (itemsCollection[i].getId() == item.getId()) {
                        if (itemsCollection[i].getTitle().contentEquals(item.getTitle())) {
                            isItemFound = true;
                            break;
                        }
                    }
                }
            }
            Assert.assertTrue("Not all items exist!", isItemFound);
        }
    }

    @Step
    public void locateItem() {
        Item itemRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        Location location = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LOCATION);
        itemRequest.setLocationId(location.getId());
        updateResource(ApiUrlConstants.UPDATE_ITEM, itemRequest, itemRequest.getId());
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.ITEM, itemRequest);
    }

    @Step
    public void checkItemLocation() {
        Location location = SerenitySessionUtils.getFromSession(SerenityKeyConstants.LOCATION);
        Item itemRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        Assert.assertTrue("The item is not in the correct location", itemRequest.getLocationId() == location.getId());
    }

    @Step
    public void cloneItem(int numberOfClones) {
        Item cloneItemRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        updateResource(ApiUrlConstants.CLONE_ITEM, cloneItemRequest, cloneItemRequest.getId(),
                String.valueOf(numberOfClones));
    }

    @Step
    public void checkTheNumberOfClones(int numberOfClones) {
        Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        Item cloneItemRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        ItemsCollection[] items = getResource(ApiUrlConstants.ITEMS + "?per_page=9999&category_id=" + category.getId(),
                ItemsCollection[].class);
        int actualNumberOfClones = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i].getTitle().contentEquals(cloneItemRequest.getTitle())) {
                actualNumberOfClones++;
            }
        }
        Assert.assertTrue(numberOfClones + " is the expected number of clones, not " + actualNumberOfClones, actualNumberOfClones == numberOfClones);
    }

    @Step
    public void createItem() {
        Item itemRequest = ItemFactory.getItemInstance();
        Item itemResponse = createResource(ApiUrlConstants.ITEMS, itemRequest, Item.class);
        itemRequest = (Item)InstanceUtils.mergeObjects(itemRequest, itemResponse);
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.ITEM, itemRequest);
        SerenitySessionUtils.saveObjectInTheListInSerenitySession(SerenityKeyConstants.ITEMS, itemRequest);
    }

    @Step
    public void renameItem() {
        Item itemRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        itemRequest.setTitle(itemRequest.getTitle() + " Updated");
        updateResource(ApiUrlConstants.UPDATE_ITEM, itemRequest, itemRequest.getId());
        SerenitySessionUtils.putOnSession(SerenityKeyConstants.ITEM, itemRequest);
    }

    @Step
    public void moveItemInAnotherCategory() {
        Item itemRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        itemRequest.setCategoryId(category.getId());
        updateResource(ApiUrlConstants.UPDATE_ITEM, itemRequest, itemRequest.getId());
    }

    @Step
    public void deleteItem() {
        Item itemRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        deleteResource(ApiUrlConstants.ITEMS, itemRequest.getId());
    }

    @Step
    public void deleteAllItemsFromCategory() {
        Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        ItemsCollection[] items = getResource(ApiUrlConstants.ITEMS + "?per_page=9999&category_id=" + category.getId(),
                ItemsCollection[].class);
        for (int i = 0; i < items.length; i++) {
            deleteResourceWithoutAssertion(ApiUrlConstants.ITEMS, items[i].getId());
        }
    }

    @Step
    public void deleteAllItemsFromCompany() {
        List<Category> categories = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORIES);
        for (Category category : categories) {
            ItemsCollection[] items = getResource(ApiUrlConstants.ITEMS + "?per_page=9999" + category.getId(),
                    ItemsCollection[].class);
            for (int i = 0; i < items.length; i++) {
                deleteResourceWithoutAssertion(ApiUrlConstants.ITEMS, items[i].getId());
            }
        }
    }

    @Step
    public void uploadImageToItem(String fileName) {
        Item itemRequest = SerenitySessionUtils.getFromSession(SerenityKeyConstants.ITEM);
        uploadResourceToEntity(ApiUrlConstants.UPLOAD_IMAGE_FILE, Constants.TEST_DATA_FILE_PATH + fileName, SerenityKeyConstants.ITEM, itemRequest.getId());
    }

    @Step
    public void uploadImageToAllItemsFromSession(String fileName) {
        List<Category> categories = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORIES);
        for (Category category : categories) {
            ItemsCollection[] items = getResource(ApiUrlConstants.ITEMS + "?per_page=9999" + category.getId(),
                    ItemsCollection[].class);
            for (int i = 0; i < items.length; i++) {
                uploadResourceToEntity(ApiUrlConstants.UPLOAD_IMAGE_FILE, Constants.TEST_DATA_FILE_PATH + fileName, SerenityKeyConstants.ITEM,
                        items[i].getId());
            }

        }
    }
}