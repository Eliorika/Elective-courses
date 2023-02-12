package ru.rsreu.electivecourses.commands.tutor;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;
import ru.rsreu.electivecourses.utils.AppUtils;

public class TutorProfileCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		User user = AppUtils.getLoginUser(request.getSession());
		request.setAttribute("type", user.getType());
		request.setAttribute("name", user.getName());
		String page = ConfigurationManager.getProperty("path.page.tutorProfile");
		return page;
	}

}
