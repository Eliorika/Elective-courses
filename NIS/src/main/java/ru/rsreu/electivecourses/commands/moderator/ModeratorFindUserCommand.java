package ru.rsreu.electivecourses.commands.moderator;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ModeratorFindUserCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.findUserModerator");
        return page;
    }
}
