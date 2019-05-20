package com.suites;

import com.tests.Test000CleanTheEnvironment;
import com.tests.apiCallsUIValidation.Test002ApiUiValidationCRUDCategory;
import com.tests.apiCallsUIValidation.Test003ApiUiValidationCRUDItem;
import com.tests.apiCallsUIValidation.Test004ApiUiValidationBookItem;
import com.tests.apiCallsUIValidation.Test007ApiUiValidationCloneItem;
import com.tests.apiCallsUIValidation.Test008ApiUiValidationCrudLocation;
import com.tests.apiCallsUIValidation.Test012ApiUiValidationBookItemMultipleTimesForDifferentPeriods;
import com.tests.apiCallsUIValidation.Test013ApiUiValidationBookItemMultipleTimesCommonPeriods;
import com.tests.apiCallsUIValidation.Test014ApiUiValidationDeleteBooking;
import com.tests.apiCallsUIValidation.Test015ApiUiValidationBookItemInThePast;
import com.tests.apiCallsUIValidation.Test016ApiUiValidationBookItemForALongPeriod;
import com.tests.apiCallsUIValidation.Test017ApiUiValidationImportItemsInTheSameCategory;
import com.tests.apiCallsUIValidation.Test018ApiUiValidationImportItemsInDifferentCategories;
import com.tests.apiCallsUIValidation.Test019ApiUiValidationBookItemForShortPeriods;
import com.tests.apiCallsUIValidation.Test020ApiUiValidationBookItemInTheFarAwayFuture;
import com.tests.apiCallsUIValidation.Test022ApiUiValidationExtendBookingWithFailure;
import com.tests.apiCallsUIValidation.Test023ApiUiValidationBookItemWithStartDateGreaterThanEndDate;
import com.tests.apiCallsUIValidation.Test024ApiUiValidationBookItemMultipleTimesWithNoInterruption;
import com.tests.apiCallsUIValidation.Test025ApiUiValidationEditBookedItem;
import com.tests.apiCallsUIValidation.Test026ApiUiValidationDeleteBookedItem;
import com.tests.apiCallsUIValidation.Test027ApiUiValidationCloneBookedItem;
import com.tests.apiCallsUIValidation.Test028ApiUiValidationBookClonedItem;
import com.tests.apiCallsUIValidation.Test029ApiUiValidationBookImportedItem;
import com.tests.apiCallsUIValidation.Test030ApiUiValidationBookItemForWholeDays;
import com.tests.apiCallsUIValidation.Test031ApiUiValidationChangeDefaultBookingLength;
import com.tests.apiCallsUIValidation.Test032ApiUiValidationChangeMaximumBookingLength;
import com.tests.apiCallsUIValidation.Test033ApiUiValidationBookImportedItem;
import com.tests.apiCallsUIValidation.Test034ApiUiValidationImportMultipleCategoriesItemsFromDownloadedCsvTemplate;
import com.tests.apiCallsUIValidation.Test035ApiUiValidationImportSameCategoryItemsFromDownloadedCsvTemplate;
import com.tests.apiCallsUIValidation.Test037ApiUiValidationMoveItemInAnotherCategory;
import com.tests.apiCallsUIValidation.Test038ApiUiValidationBookItemAsRegularUser;
import com.tests.apiCallsUIValidation.Test039ApiUiValidationAcceptSupervisionedBooking;
import com.tests.apiCallsUIValidation.Test040ApiUiValidationDeclineSupervisionedBooking;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    Test000CleanTheEnvironment.class,
    Test002ApiUiValidationCRUDCategory.class,
    Test003ApiUiValidationCRUDItem.class,
    Test004ApiUiValidationBookItem.class,
        //        Test005ApiUiValidationReturnItem.class,
        //        Test006ApiUiValidationExtendBooking.class,
    Test007ApiUiValidationCloneItem.class,
    Test008ApiUiValidationCrudLocation.class,
    Test012ApiUiValidationBookItemMultipleTimesForDifferentPeriods.class,
    Test013ApiUiValidationBookItemMultipleTimesCommonPeriods.class,
    Test014ApiUiValidationDeleteBooking.class,
    Test015ApiUiValidationBookItemInThePast.class,
    Test016ApiUiValidationBookItemForALongPeriod.class,
    Test017ApiUiValidationImportItemsInTheSameCategory.class,
    Test018ApiUiValidationImportItemsInDifferentCategories.class,
    Test019ApiUiValidationBookItemForShortPeriods.class,
    Test020ApiUiValidationBookItemInTheFarAwayFuture.class,
    Test022ApiUiValidationExtendBookingWithFailure.class,
    Test023ApiUiValidationBookItemWithStartDateGreaterThanEndDate.class,
    Test024ApiUiValidationBookItemMultipleTimesWithNoInterruption.class,
    Test025ApiUiValidationEditBookedItem.class,
    Test026ApiUiValidationDeleteBookedItem.class,
    Test027ApiUiValidationCloneBookedItem.class,
    Test028ApiUiValidationBookClonedItem.class,
    Test029ApiUiValidationBookImportedItem.class,
    Test030ApiUiValidationBookItemForWholeDays.class,
    Test031ApiUiValidationChangeDefaultBookingLength.class,
    Test032ApiUiValidationChangeMaximumBookingLength.class,
    Test033ApiUiValidationBookImportedItem.class,
    Test034ApiUiValidationImportMultipleCategoriesItemsFromDownloadedCsvTemplate.class,
    Test035ApiUiValidationImportSameCategoryItemsFromDownloadedCsvTemplate.class,
    Test037ApiUiValidationMoveItemInAnotherCategory.class,
    Test038ApiUiValidationBookItemAsRegularUser.class,
    Test039ApiUiValidationAcceptSupervisionedBooking.class,
    Test040ApiUiValidationDeclineSupervisionedBooking.class,
    //        Test041ApiUiValidationAcceptSupervisionedExtendedBooking.class,
    //        Test042ApiUiValidationDeclineSupervisionedExtendedBooking.class,
})
public class TestsSuiteApiCallsUIValidation {

}
