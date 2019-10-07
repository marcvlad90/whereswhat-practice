package com.tools.factories;

import com.tools.constants.EnvironmentConstants;
import com.tools.entities.User;

public class UserFactory {
    public static User getUserInstance() {
        User user = new User();
        user.setEmail(EnvironmentConstants.ADMIN_USER);
        user.setPassword(EnvironmentConstants.ADMIN_USER_PASS);
        return user;
    }
}
