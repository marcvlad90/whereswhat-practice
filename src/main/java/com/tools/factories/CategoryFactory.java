package com.tools.factories;

import net.bytebuddy.utility.RandomString;

import com.tools.entities.Category;
import com.tools.entities.CustomField;

public class CategoryFactory {
    public static Category getCategoryInstance() {
        Category category = new Category();
        category.setName(RandomString.make(10) + " - Category");
        return category;
    }

    public static Category getSupervisionedCategoryInstance() {
        Category category = new Category();
        category.setName(RandomString.make(10) + " - Category");
        category.setNeedsApproval(true);
        return category;
    }

    public static Category getCustomFieldsCategoryInstance(int numberOfCustomAttributes) {
        Category category = new Category();
        CustomField[] categoryCustomFields = new CustomField[numberOfCustomAttributes];
        for (int i = 0; i < numberOfCustomAttributes; i++) {
            categoryCustomFields[i] = new CustomField();
            categoryCustomFields[i].setName(RandomString.make(10) + " - Custom Field");
        }
        category.setCategoryCustomFields(categoryCustomFields);
        category.setName(RandomString.make(10) + " - Category");
        return category;
    }
}
