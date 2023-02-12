package ru.rsreu.electivecourses.utils;

import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.datalayer.data.UserType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;

public class UserRoleRequestWrapper extends HttpServletRequestWrapper {

    private final User user;
    private final UserType userType;
    private final HttpServletRequest request;

    public UserRoleRequestWrapper(HttpServletRequest request, User user, UserType userType) {
        super(request);
        this.user = user;
        this.userType = userType;
        this.request = request;
    }

    @Override
    public boolean isUserInRole(String role) {
        if (role == null) {
            return this.request.isUserInRole(null);
        }

        return this.userType.toString().equalsIgnoreCase(role);
    }

    @Override
    public Principal getUserPrincipal() {
        if (this.user == null) {
            return this.request.getUserPrincipal();
        }
        return user::getLogin;
    }
}
