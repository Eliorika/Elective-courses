package ru.rsreu.electivecourses.commands.tutor;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;

import static ru.rsreu.electivecourses.commands.tutor.DocsCommand.getTutorCourses;

public class FinalDocsCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.finalDocs");
		request.setAttribute("listCourse", getTutorCourses(request));
		return page;
	}

}
