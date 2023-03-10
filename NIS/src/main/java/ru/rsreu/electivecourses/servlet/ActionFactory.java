package ru.rsreu.electivecourses.servlet;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.commands.CommandEnum;
import ru.rsreu.electivecourses.commands.common.EmptyCommand;
import ru.rsreu.electivecourses.propertiesmanagers.MessageManager;

public class ActionFactory {
	public ActionFactory() {
	}
	
	public ActionCommand defineCommand(HttpServletRequest request) {
		ActionCommand current = new EmptyCommand();
		String action = request.getParameter("command");
		if (action == null || action.isEmpty()) {
			return current;
		}
		try {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
			current = currentEnum.getCurrentCommand();
		} catch(IllegalArgumentException e) {
			request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
		}
		return current;
		
	}
}
