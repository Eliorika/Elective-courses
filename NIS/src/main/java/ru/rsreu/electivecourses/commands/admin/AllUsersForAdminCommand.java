package ru.rsreu.electivecourses.commands.admin;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.commands.moderator.AllUsersForModeratorCommand;
import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class AllUsersForAdminCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        AllUsersForModeratorCommand.getAllUsers(request);
        String page = ConfigurationManager.getProperty("path.page.allUsersAdmin");
        return page;
    }

}
