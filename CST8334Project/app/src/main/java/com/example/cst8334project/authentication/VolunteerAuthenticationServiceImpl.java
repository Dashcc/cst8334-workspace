package com.example.cst8334project.authentication;

import android.util.Log;

import org.apache.commons.lang3.StringUtils;

/**
 * The implementation of the {@link VolunteerAuthenticationService} interface.
 */
public final class VolunteerAuthenticationServiceImpl implements VolunteerAuthenticationService {

    /**
     * The name of this class for logging purposes.
     */
    private static final String CLASS_NAME = VolunteerAuthenticationServiceImpl.class.getSimpleName();

    @Override
    public boolean authenticate(String username, String password) {
        // Method parameter validation
        if (StringUtils.isAnyBlank(username, password)) {
            Log.e(CLASS_NAME, "Cannot authenticate volunteer " +
                    "with null/empty username or password");
            return false;
        }

        // Normalize the provided parameters
        username = StringUtils.strip(username);
        password = StringUtils.strip(password);

        Log.i(CLASS_NAME, "Authenticating [username : password] = [" +
                username + " : " + password + "].");

        if (username.equals(LoginCredentialsManager.getUserName())
                && password.equals(LoginCredentialsManager.getPassword())) {
            Log.i(CLASS_NAME, "Authentication succeeded for [username : password] = [" +
                    username + " : " + password + "].");
            return true;
        }

        Log.e(CLASS_NAME, "Authentication failed for [username : password] = [" +
               username + " : " + password + "].");
        return false;
    }
}
