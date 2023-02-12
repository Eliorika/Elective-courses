package ru.rsreu.electivecourses.commands.student;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.datalayer.data.Course;
import ru.rsreu.electivecourses.datalayer.oracledb.CourseDAO;
import ru.rsreu.electivecourses.datalayer.oracledb.DAOFactory;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;

import java.sql.SQLException;
import java.util.List;

public class SignCourseCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		DAOFactory factory = null;
		try {
			factory = DAOFactory.getInstance();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		CourseDAO courseDAO = factory.getCourseDAO();
		List<Course> allCourses = courseDAO.getAllCourses();
		request.setAttribute("coursesList", allCourses);
		String page = ConfigurationManager.getProperty("path.page.signCourse");
		return page;
	}

}
