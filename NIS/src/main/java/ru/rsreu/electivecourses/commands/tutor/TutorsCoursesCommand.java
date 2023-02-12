package ru.rsreu.electivecourses.commands.tutor;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.datalayer.data.Course;
import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.datalayer.oracledb.CourseDAO;
import ru.rsreu.electivecourses.datalayer.oracledb.DAOFactory;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;
import ru.rsreu.electivecourses.utils.AppUtils;

import java.sql.SQLException;
import java.util.List;

public class TutorsCoursesCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		request.setAttribute("coursesList", DocsCommand.getTutorCourses(request));
		String page = ConfigurationManager.getProperty("path.page.tutorCourses");
		return page;
	}

}
