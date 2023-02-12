package ru.rsreu.electivecourses.utils;

import ru.rsreu.electivecourses.datalayer.data.User;

import javax.servlet.http.HttpSession;

public class AppUtils {

    public static User getLoginUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    public static void storeLoginUser(HttpSession session, User user) {
        session.setAttribute("user", user);
    }

}
