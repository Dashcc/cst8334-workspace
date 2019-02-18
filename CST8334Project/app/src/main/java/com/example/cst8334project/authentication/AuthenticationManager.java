package com.example.cst8334project.authentication;

import android.util.Log;

import com.example.cst8334project.emailservice.EmailConstants;

import org.apache.commons.lang3.StringUtils;

/**
 * Singleton that manages and authenticates the login credentials for volunteers.
 */
public final class AuthenticationManager {

    /**
     * The name of this class for logging purposes.
     */
    private static final String CLASS_NAME = AuthenticationManager.class.getSimpleName();

    /**
     * The default username that will be used to authenticate volunteers if an
     * alternate username has not been provided by the client.
     */
    private static final String DEFAULT_USERNAME = "volun";

    /**
     * The default password that will be used to authenticate volunteers if an
     * alternate password has not been provided by the client.
     */
    private static final String DEFAULT_PASSWORD = "123";

    /**
     * The username used to authenticate all volunteers.
     */
    private static String username = DEFAULT_USERNAME;

    /**
     * The password used to authenticate all volunteers.
     */
    private static String password = DEFAULT_PASSWORD;

    /**
     * @param username the username to set
     */
    static void setUsername(String username) {
        username = StringUtils.strip(username);
        Log.i(CLASS_NAME, "Setting the username to: " + username);
        AuthenticationManager.username = username;
    }

    /**
     * @param password the password to set
     */
    static void setPassword(String password) {
        password = StringUtils.strip(password);
        Log.i(CLASS_NAME, "Setting the password to: " + password);
        AuthenticationManager.password = password;
    }

    /**
     * @return username
     */
    static String getUserName() {
        return username;
    }

    /**
     * @return password
     */
    static String getPassword() {
        return password;
    }

    /**
     * Determine if the provided email address matches the one used the client for the purposes
     * of providing new authentication credentials for this application.
     *
     * @param emailAddress the email address to validate
     * @return {@code true} if the email address matches the client email address, {@code false}
     * otherwise
     */
    static boolean isEmailFromClient(String emailAddress) {
        return StringUtils.equalsIgnoreCase(EmailConstants.RECIPIENT_EMAIL_ADDRESS, emailAddress);
    }

    /**
     * Authenticate the provided volunteer username and password.
     *
     * @param username the volunteer username
     * @param password the volunteer password
     * @return {@code true} if authentication succeeded, {@code false} otherwise
     */
    public static boolean authenticate(String username, String password) {
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

        if (username.equals(getUserName()) && password.equals(getPassword())) {
            Log.i(CLASS_NAME, "Authentication succeeded for [username : password] = [" +
                    username + " : " + password + "].");
            return true;
        }

        Log.e(CLASS_NAME, "Authentication failed for [username : password] = [" +
                username + " : " + password + "].");
        return false;
    }

    /**
     * Prevent instantiation.
     */
    private AuthenticationManager() {
    }
}
