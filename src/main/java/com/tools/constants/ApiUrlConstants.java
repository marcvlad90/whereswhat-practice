package com.tools.constants;

public class ApiUrlConstants {
    public static final String LOGIN = "/users/sign_in";
    public static final String LOGOUT = "/users/sign_out";
    public static final String CATEGORIES = "/Categories";
    public static final String CATEGORY = "/Category";
    public static final String ITEMS = "/Items";
    public static final String BOOKINGS = "/Bookings";
    public static final String UPDATE_CATEGORY = "/Categories/{id}";
    public static final String UPDATE_ITEM = "/Items/{id}";
    public static final String CLONE_ITEM = "/Items/{id}?clone_number={numberOfClones}";
    public static final String UPDATED_BOOKING = "/Bookings/{id}";
    public static final String BASE_URI = EnvironmentConstants.BASE_URL + "/api";
    public static final String LOCATIONS = "/Locations";
    public static final String USER_SETTINGS = "UserSettings";
    public static final String UPLOAD_IMAGE_FILE = "/FileUpload/file_image";
    public static final String CSV_UPLOAD = "/FileUpload/csv";
    public static final String PROCESS_CSV_FILE = "/ProcessFile/csv";
}
