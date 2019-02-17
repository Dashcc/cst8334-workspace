package com.example.cst8334project.authentication;

/**
 * Interface that exposes method(s) to authenticate volunteers.
 */
public interface VolunteerAuthenticationService {

    /**
     * Authenticate the provided volunteer username and password.
     *
     * @param username the volunteer username
     * @param password the volunteer password
     * @return {@code true} if authentication succeeded, {@code false} otherwise
     */
    boolean authenticate(String username, String password);
}
