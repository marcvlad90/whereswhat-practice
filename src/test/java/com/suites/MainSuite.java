package com.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    TestsSuiteApiCalls.class,
    TestsSuiteUI.class,
    TestsSuiteApiCallsUIValidation.class,
})
public class MainSuite {

}
