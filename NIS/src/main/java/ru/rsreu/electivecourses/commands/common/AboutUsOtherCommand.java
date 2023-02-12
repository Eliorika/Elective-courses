package ru.rsreu.electivecourses.commands.common;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AboutUsOtherCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.aboutOther");
        return page;
    }


}
