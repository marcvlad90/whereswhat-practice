package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.tests.apiCalls.Test001ApiLogin;
import com.tests.apiCalls.Test002ApiCRUDCategory;
import com.tests.apiCalls.Test003ApiCRUDItem;
import com.tests.apiCalls.Test004ApiBookItem;
import com.tests.apiCalls.Test005ApiReturnItem;
import com.tests.apiCalls.Test006ApiExtendBooking;
import com.tests.apiCalls.Test007ApiCloneItem;
import com.tests.apiCalls.Test010ApiUploadImageForCategoryAndItem;
import com.tests.apiCalls.Test011ApiEmailNotification;
import com.tests.apiCalls.Test012ApiBookItemMultipleTimesForDifferentPeriods;
import com.tests.apiCalls.Test013ApiBookItemMultipleTimesCommonPeriods;
import com.tests.apiCalls.Test014ApiDeleteBooking;
import com.tests.apiCalls.Test015ApiBookItemInThePast;
import com.tests.apiCalls.Test016ApiBookItemForALongPeriod;
import com.tests.apiCalls.Test017ApiImportItemsInTheSameCategory;
import com.tests.apiCalls.Test018ApiImportItemsInDifferentCategories;
import com.tests.apiCalls.Test019ApiBookItemForShortPeriods;
import com.tests.apiCalls.Test020ApiBookItemInTheFarAwayFuture;
import com.tests.apiCalls.Test022ApiExtendBookingWithFailure;
import com.tests.apiCalls.Test023ApiBookItemWithStartDateGreaterThanEndDate;
import com.tests.apiCalls.Test024ApiBookItemMultipleTimesWithNoInterruption;
import com.tests.apiCalls.Test025ApiEditBookedItem;
import com.tests.apiCalls.Test026ApiDeleteBookedItem;
import com.tests.apiCalls.Test027ApiCloneBookedItem;
import com.tests.apiCalls.Test028ApiBookClonedItem;
import com.tests.apiCalls.Test029ApiBookImportedItem;
import com.tests.apiCalls.Test030ApiBookItemForWholeDays;
import com.tests.apiCalls.Test031ApiChangeDefaultBookingLength;
import com.tests.apiCalls.Test032ApiChangeMaximumBookingLength;
import com.tests.apiCalls.Test033ApiBookImportedItem;
import com.tests.apiCalls.Test034ApiImportMultipleCategoriesItemsFromDownloadedCsvTemplate;
import com.tests.apiCalls.Test035ApiImportSameCategoryItemsFromDownloadedCsvTemplate;
import com.tests.apiCalls.Test037ApiMoveItemInAnotherCategory;
import com.tests.apiCalls.Test038ApiBookItemAsRegularUser;
import com.tests.apiCalls.Test039ApiAcceptSupervisionedBooking;
import com.tests.apiCalls.Test040ApiDeclineSupervisionedBooking;
import com.tests.apiCalls.Test041ApiAcceptSupervisionedExtendedBooking;
import com.tests.apiCalls.Test042ApiDeclineSupervisionedExtendedBooking;

@RunWith(Suite.class)
@SuiteClasses({
    //Test000CleanTheEnvironment.class,
    Test001ApiLogin.class,
    Test002ApiCRUDCategory.class,
    Test003ApiCRUDItem.class,
    Test004ApiBookItem.class,
    Test005ApiReturnItem.class,
    Test006ApiExtendBooking.class,
    Test007ApiCloneItem.class,
    Test010ApiUploadImageForCategoryAndItem.class,
    Test011ApiEmailNotification.class,
    Test012ApiBookItemMultipleTimesForDifferentPeriods.class,
    Test013ApiBookItemMultipleTimesCommonPeriods.class,
    Test014ApiDeleteBooking.class,
    Test015ApiBookItemInThePast.class,
    Test016ApiBookItemForALongPeriod.class,
    Test017ApiImportItemsInTheSameCategory.class,
    Test018ApiImportItemsInDifferentCategories.class,
    Test019ApiBookItemForShortPeriods.class,
    Test020ApiBookItemInTheFarAwayFuture.class,
    Test022ApiExtendBookingWithFailure.class,
    Test023ApiBookItemWithStartDateGreaterThanEndDate.class,
    Test024ApiBookItemMultipleTimesWithNoInterruption.class,
    Test025ApiEditBookedItem.class,
    Test026ApiDeleteBookedItem.class,
    Test027ApiCloneBookedItem.class,
    Test028ApiBookClonedItem.class,
    Test029ApiBookImportedItem.class,
    Test030ApiBookItemForWholeDays.class,
    Test031ApiChangeDefaultBookingLength.class,
    Test032ApiChangeMaximumBookingLength.class,
    Test033ApiBookImportedItem.class,
    Test034ApiImportMultipleCategoriesItemsFromDownloadedCsvTemplate.class,
    Test035ApiImportSameCategoryItemsFromDownloadedCsvTemplate.class,
    Test037ApiMoveItemInAnotherCategory.class,
    Test038ApiBookItemAsRegularUser.class,
    Test039ApiAcceptSupervisionedBooking.class,
    Test040ApiDeclineSupervisionedBooking.class,
    Test041ApiAcceptSupervisionedExtendedBooking.class,
    Test042ApiDeclineSupervisionedExtendedBooking.class,
})
public class TestsSuiteApiCalls {

}
