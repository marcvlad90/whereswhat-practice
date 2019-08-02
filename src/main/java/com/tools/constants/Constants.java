package com.tools.constants;

import java.io.File;

public class Constants {
    public static final String TEST_DATA_FILE_PATH = "src/test/resources/files/";
    public static final String WEB_DRIVERS_PATH = "src/test/resources/drivers/";
    public static final String DOWNLOAD_FILE_PATH = System.getProperty("user.home") + File.separator;
    //header menu items
    public static final String MENU_ITEM_ITEMS = "ITEMS";
    public static final String MENU_ITEM_BOOKINGS = "BOOKINGS";
    //bookings tabs
    public static final String BOOKINGS_TAB_MY_BOOKINGS = "My Bookings";
    public static final String BOOKINGS_TAB_ALL_BOOKINGS = "All Bookings";
    public static final String BOOKINGS_TAB_BOOKING_REQUESTS = "Booking Requests";
    //profile menu item action
    public static final String PROFILE_MENU_ITEM_SETTINGS = "Settings";
    public static final String PROFILE_MENU_ITEM_SIGNOUT = "Sign out";
    //wait custom time
    public static final int WAIT_TIME_ONE_SECOND_IN_MILISECONDS = 1000;
    public static final int WAIT_TIME_MAXIMUM_IN_SECONDS = 30;
    public static final int WAIT_TIME_MAXIMUM_IN_MILISECONDS = 30000;
    public static final int WAIT_TIME_FLUENT_WAIT_POLLING_IN_MILISECONDS = 500;
    //items page actions
    public static final String ITEMS_PAGE_ACTION_ADD_CATEGORY = "Add Category";
    public static final String ITEMS_PAGE_ACTION_ADD_ITEM = "Add Item";
    public static final String ITEMS_PAGE_ACTION_IMPORT_ITEMS = "Import Items";
    //items and categories actions
    public static final String ACTION_EDIT = "Edit";
    public static final String ACTION_DELETE = "Delete";
    public static final String ACTION_CLONE = "Clone";
}
