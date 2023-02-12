package ru.rsreu.electivecourses.commands.common;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;

public class AuthorizationCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.authorization");
		return page;
	}

}
