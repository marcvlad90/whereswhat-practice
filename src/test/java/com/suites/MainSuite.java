package com.suites;

import com.tests.Test000CleanTheEnvironment;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    Test000CleanTheEnvironment.class,
    TestsSuiteApiCalls.class,
    TestsSuiteUI.class,
    TestsSuiteApiCallsUIValidation.class,
})
public class MainSuite {

}
