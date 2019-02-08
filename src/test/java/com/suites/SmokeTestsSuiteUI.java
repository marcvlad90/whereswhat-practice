package com.suites;

import com.tests.Test000CleanTheEnvironment;
import com.tests.UI.Test002UiCRUDCategory;
import com.tests.UI.Test003UiCRUDItem;
import com.tests.UI.Test004UiBookItem;
import com.tests.UI.Test005UiReturnItem;
import com.tests.UI.Test006UiExtendBooking;
import com.tests.UI.Test007UiCloneItem;
import com.tests.UI.Test011UiEmailNotification;
import com.tests.UI.Test014UiDeleteBooking;
import com.tests.UI.Test018UiImportItemsInDifferentCategories;
import com.tests.UI.Test021UiBookItemFromCalendar;
import com.tests.UI.Test034UiImportMultipleCategoriesItemsFromDownloadedCsvTemplate;
import com.tests.UI.Test036UiSearchItemByTag;
import com.tests.UI.Test038UiBookItemAsRegularUser;
import com.tests.UI.Test039UiAcceptSupervisionedBooking;
import com.tests.UI.Test040UiDeclineSupervisionedBooking;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        Test000CleanTheEnvironment.class,
        Test002UiCRUDCategory.class,
        Test003UiCRUDItem.class,
        Test004UiBookItem.class,
        Test005UiReturnItem.class,
        Test006UiExtendBooking.class,
        Test007UiCloneItem.class,
        Test011UiEmailNotification.class,
        Test014UiDeleteBooking.class,
        Test018UiImportItemsInDifferentCategories.class,
        Test021UiBookItemFromCalendar.class,
        Test034UiImportMultipleCategoriesItemsFromDownloadedCsvTemplate.class,
        Test036UiSearchItemByTag.class,
        Test038UiBookItemAsRegularUser.class,
        Test039UiAcceptSupervisionedBooking.class,
        Test040UiDeclineSupervisionedBooking.class,
})
public class SmokeTestsSuiteUI {

}
