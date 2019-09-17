package com.tools.factories;

import java.util.ArrayList;
import java.util.List;

import net.bytebuddy.utility.RandomString;

import com.tools.constants.SerenityKeyConstants;
import com.tools.entities.Category;
import com.tools.entities.CustomField;
import com.tools.entities.Item;
import com.tools.utils.SerenitySessionUtils;

public class ItemFactory {
    public static Item getItemInstance() {
        Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        Item item = new Item();
        item.setTitle(RandomString.make(10) + " - Item");
        item.setCategoryId(category.getId());
        item.setItemCustomFields(new CustomField[0]);
        item.setRetainedImage(null);
        item.setItemCode(null);
        item.setItemCode("Tag" + RandomString.make(10));
        return item;
    }

    public static Item getItemWithCustomFieldsInstance() {
        Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        int numberOfAttributes = category.getCustomFields().length;
        CustomField[] itemCustomFields = new CustomField[numberOfAttributes];
        for (int i = 0; i < numberOfAttributes; i++) {
            itemCustomFields[i] = new CustomField();
            itemCustomFields[i].setValue(category.getCustomFields()[i].getName() + " Value");
            itemCustomFields[i].setCustomFieldId(category.getCustomFields()[i].getId());
            itemCustomFields[i].setCustomFieldName(category.getCustomFields()[i].getName());
        }
        Item item = new Item();
        item.setTitle(RandomString.make(10) + " - Item");
        item.setCategoryId(category.getId());
        item.setItemCustomFields(itemCustomFields);
        item.setItemCode("Tag" + RandomString.make(10));
        return item;
    }

    public static List<Item> getItemSameCategoryCSVInstanceList(int numberOfItems) {
        Category category = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORY);
        List<Item> items = new ArrayList<Item>();
        for (int i = 0; i < numberOfItems; i++) {
            Item item = new Item();
            //item clasic properties
            item.setTitle(RandomString.make(10) + " - Item");
            item.setItemCustomFields(new CustomField[0]);
            item.setCategoryId(category.getId());
            item.setCategory(category);

            //csv properties
            item.setName(item.getTitle());
            item.setItemTag(item.getTitle() + "_tag");
            item.setCategoryTitle(category.getName());
            items.add(item);
        }
        return items;
    }

    public static List<Item> getItemDifferentCategoriesCSVInstanceList(int numberOfItems) {
        List<Category> categories = SerenitySessionUtils.getFromSession(SerenityKeyConstants.CATEGORIES);
        List<Item> items = new ArrayList<Item>();
        for (Category category : categories) {
            for (int i = 0; i < numberOfItems; i++) {
                Item item = new Item();
                //item clasic properties
                item.setTitle(RandomString.make(10) + " - Item");
                item.setItemCustomFields(new CustomField[0]);
                item.setCategoryId(category.getId());
                item.setCategory(category);

                //csv properties
                item.setName(item.getTitle());
                item.setItemTag(item.getTitle() + "_tag");
                item.setCategoryTitle(category.getName());

                items.add(item);
            }
        }
        return items;
    }
}
