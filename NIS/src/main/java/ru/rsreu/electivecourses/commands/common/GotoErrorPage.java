package ru.rsreu.electivecourses.commands.common;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class GotoErrorPage implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.error");
        return page;
    }
}
