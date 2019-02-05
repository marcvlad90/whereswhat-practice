package com.tools.constants;

import com.tools.persistence.PropertyFileReader;

public class EnvironmentConstants {
    public static final String BASE_URL = PropertyFileReader.getEnvConstantsItem("BASE_URL");
    public static final String ADMIN_USER = PropertyFileReader.getEnvConstantsItem("ADMIN_USER");
    public static final String ADMIN_USER_PASS = PropertyFileReader.getEnvConstantsItem("ADMIN_USER_PASS");
    public static final String ADMIN_USER_NAME = PropertyFileReader.getEnvConstantsItem("ADMIN_USER_NAME");
    public static final String EMAIL_PASS = PropertyFileReader.getEnvConstantsItem("EMAIL_PASS");
    public static final String REGULAR_USER = PropertyFileReader.getEnvConstantsItem("REGULAR_USER");
    public static final String REGULAR_USER_PASS = PropertyFileReader.getEnvConstantsItem("REGULAR_USER_PASS");
    public static final String REGULAR_USER_NAME = PropertyFileReader.getEnvConstantsItem("REGULAR_USER_NAME");
}
