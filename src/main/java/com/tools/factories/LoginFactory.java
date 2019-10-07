package com.tools.factories;

import com.tools.constants.EnvironmentConstants;
import com.tools.entities.Login;
import com.tools.entities.User;

public class LoginFactory {
    public static Login getAdminLoginInstance() {
        Login login = new Login();
        User user = new User();
        user.setEmail(EnvironmentConstants.ADMIN_USER);
        user.setPassword(EnvironmentConstants.ADMIN_USER_PASS);
        login.setUser(user);
        return login;
    }

    public static Login getRegularUserLoginInstance() {
        Login login = new Login();
        User user = new User();
        user.setEmail(EnvironmentConstants.REGULAR_USER);
        user.setPassword(EnvironmentConstants.REGULAR_USER_PASS);
        user.setName(EnvironmentConstants.REGULAR_USER_NAME);
        login.setUser(user);
        return login;
    }
}
