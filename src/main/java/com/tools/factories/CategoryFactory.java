package com.tools.factories;

import com.tools.entities.Category;

import net.bytebuddy.utility.RandomString;

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

}
