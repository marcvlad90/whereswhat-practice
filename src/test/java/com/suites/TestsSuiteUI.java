package com.suites;

import com.tests.Test000CleanTheEnvironment;
import com.tests.UI.Test001UiLogin;
import com.tests.UI.Test002UiCRUDCategory;
import com.tests.UI.Test003UiCRUDItem;
import com.tests.UI.Test004UiBookItem;
import com.tests.UI.Test007UiCloneItem;
import com.tests.UI.Test011UiEmailNotification;
import com.tests.UI.Test012UiBookItemMultipleTimesForDifferentPeriods;
import com.tests.UI.Test013UiBookItemMultipleTimesCommonPeriods;
import com.tests.UI.Test014UiDeleteBooking;
import com.tests.UI.Test015UiBookItemInThePast;
import com.tests.UI.Test016UiBookItemForALongPeriod;
import com.tests.UI.Test017UiImportItemsInTheSameCategory;
import com.tests.UI.Test018UiImportItemsInDifferentCategories;
import com.tests.UI.Test019UiBookItemForShortPeriods;
import com.tests.UI.Test020UiBookItemInTheFarAwayFuture;
import com.tests.UI.Test021UiBookItemFromCalendar;
import com.tests.UI.Test023UiBookItemWithStartDateGreaterThanEndDate;
import com.tests.UI.Test024UiBookItemMultipleTimesWithNoInterruption;
import com.tests.UI.Test025UiEditBookedItem;
import com.tests.UI.Test026UiDeleteBookedItem;
import com.tests.UI.Test027UiCloneBookedItem;
import com.tests.UI.Test028UiBookClonedItem;
import com.tests.UI.Test029UiBookImportedItem;
import com.tests.UI.Test030UiBookItemForWholeDays;
import com.tests.UI.Test031UiChangeDefaultBookingLength;
import com.tests.UI.Test032UiChangeMaximumBookingLength;
import com.tests.UI.Test033UiBookImportedItem;
import com.tests.UI.Test034UiImportMultipleCategoriesItemsFromDownloadedCsvTemplate;
import com.tests.UI.Test035UiImportSameCategoryItemsFromDownloadedCsvTemplate;
import com.tests.UI.Test036UiSearchItemByTag;
import com.tests.UI.Test037UiMoveItemInAnotherCategory;
import com.tests.UI.Test038UiBookItemAsRegularUser;
import com.tests.UI.Test039UiAcceptSupervisionedBooking;
import com.tests.UI.Test040UiDeclineSupervisionedBooking;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        Test000CleanTheEnvironment.class,
        Test001UiLogin.class,
        Test002UiCRUDCategory.class,
        Test003UiCRUDItem.class,
        Test004UiBookItem.class,
        //    Test005UiReturnItem.class,
        //    Test006UiExtendBooking.class,
        Test007UiCloneItem.class,
        Test011UiEmailNotification.class,
        Test012UiBookItemMultipleTimesForDifferentPeriods.class,
        Test013UiBookItemMultipleTimesCommonPeriods.class,
        Test014UiDeleteBooking.class,
        Test015UiBookItemInThePast.class,
        Test016UiBookItemForALongPeriod.class,
        Test017UiImportItemsInTheSameCategory.class,
        Test018UiImportItemsInDifferentCategories.class,
        Test019UiBookItemForShortPeriods.class,
        Test020UiBookItemInTheFarAwayFuture.class,
        Test021UiBookItemFromCalendar.class,
        //    Test022UiExtendBookingWithFailure.class,
        Test023UiBookItemWithStartDateGreaterThanEndDate.class,
        Test024UiBookItemMultipleTimesWithNoInterruption.class,
        Test025UiEditBookedItem.class,
        Test026UiDeleteBookedItem.class,
        Test027UiCloneBookedItem.class,
        Test028UiBookClonedItem.class,
        Test029UiBookImportedItem.class,
        Test030UiBookItemForWholeDays.class,
        Test031UiChangeDefaultBookingLength.class,
        Test032UiChangeMaximumBookingLength.class,
        Test033UiBookImportedItem.class,
        Test034UiImportMultipleCategoriesItemsFromDownloadedCsvTemplate.class,
        Test035UiImportSameCategoryItemsFromDownloadedCsvTemplate.class,
        Test036UiSearchItemByTag.class,
        Test037UiMoveItemInAnotherCategory.class,
        Test038UiBookItemAsRegularUser.class,
        Test039UiAcceptSupervisionedBooking.class,
        Test040UiDeclineSupervisionedBooking.class,
//    Test041UiAcceptSupervisionedExtendedBooking.class,
//    Test042UiDeclineSupervisionedExtendedBooking.class,
})
public class TestsSuiteUI {

}
