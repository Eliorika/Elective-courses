package ru.rsreu.electivecourses.commands.moderator;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;
import ru.rsreu.electivecourses.utils.AppUtils;

import javax.servlet.http.HttpServletRequest;

public class ModeratorProfileCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        User user = AppUtils.getLoginUser(request.getSession());
        request.setAttribute("type", user.getType());
        request.setAttribute("name", user.getName());
        String page = ConfigurationManager.getProperty("path.page.moderatorProfile");
        return page;
    }
}
