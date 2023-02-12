package ru.rsreu.electivecourses.datalayer.data;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Data class for users. Stores information about users states
 */
public @Data class User {
    /**
     * Identification number of a user
     */
    private int id;
    /**
     * Login to system of a user
     */
    private String login;
    /**
     * Password to the system for user
     */
    private String password;
    /**
     * Is user logged in
     */
    private boolean is_active;
    /**
     * Is user blocked
     */
    private boolean is_blocked;
    /**
     * Type of user. See {@link ru.rsreu.electivecourses.datalayer.data.UserType} for details
     */
    private UserType type;
    /**
     * Name of user
     */
    private String name;


    /**
     * Last logged in user
     */
    private static User loggedUser = null;

    /**
     * Constructor to create new user
     */
    public User(){
    }

    /**
     * Get the static field User
     * @return user
     */
    public static User getLoggedUser() {
        return loggedUser;
    }

    /**
     * Set the static field User
     * @return user
     */
    public static void setLoggedUser(User loggedUser) {
        User.loggedUser = loggedUser;
    }
}
