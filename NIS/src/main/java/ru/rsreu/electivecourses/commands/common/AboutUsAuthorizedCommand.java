package ru.rsreu.electivecourses.commands.common;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;

import java.io.IOException;

public class AboutUsAuthorizedCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.aboutUsAuthorized");
		return page;
	}

}
