package ru.rsreu.electivecourses.commands.common;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;

public class EmptyCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page;
		page = ConfigurationManager.getProperty("path.page.index");
		return page;
	}

}
