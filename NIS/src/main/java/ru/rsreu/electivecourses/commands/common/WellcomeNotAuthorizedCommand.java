package ru.rsreu.electivecourses.commands.common;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;

public class WellcomeNotAuthorizedCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.welcomeNotAuthorized");
		return page;
	}

}
