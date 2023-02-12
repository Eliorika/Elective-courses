package ru.rsreu.electivecourses.commands.common;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.datalayer.data.UserType;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;
import ru.rsreu.electivecourses.utils.AppUtils;

import javax.servlet.http.HttpServletRequest;

public class ProfileCommand implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request)  {
        String page = null;
        User user = AppUtils.getLoginUser(request.getSession());
        request.setAttribute("type", user.getName());
        request.setAttribute("name", user.getName());
        if (UserType.STUDENT.equals(user.getType())) {
            page = ConfigurationManager.getProperty("path.page.studentProfile");
        } else if (UserType.TUTOR.equals(user.getType())) {
            page = ConfigurationManager.getProperty("path.page.tutorProfile");
        } else if (UserType.ADMIN.equals(user.getType())) {
            page = ConfigurationManager.getProperty("path.page.adminProfile");
        } else if (UserType.MODERATOR.equals(user.getType())) {
            page = ConfigurationManager.getProperty("path.page.moderatorProfile");
        }
        return page;
    }
}
