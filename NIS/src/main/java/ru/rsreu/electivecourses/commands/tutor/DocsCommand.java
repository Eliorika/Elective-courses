package ru.rsreu.electivecourses.commands.tutor;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.datalayer.data.Course;
import ru.rsreu.electivecourses.datalayer.data.StudentsCourse;
import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.datalayer.oracledb.CourseDAO;
import ru.rsreu.electivecourses.datalayer.oracledb.DAOFactory;
import ru.rsreu.electivecourses.datalayer.oracledb.StudentsCourseDAO;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;
import ru.rsreu.electivecourses.utils.AppUtils;

import java.sql.SQLException;
import java.util.List;

import static ru.rsreu.electivecourses.commands.student.StudentMarksCommand.getStudentCourses;

public class DocsCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		request.setAttribute("listCourse", getTutorCourses(request));
		String page = ConfigurationManager.getProperty("path.page.docs");
		return page;
	}

	public static List<Course> getTutorCourses(HttpServletRequest request) {
		User user = AppUtils.getLoginUser(request.getSession());
		DAOFactory factory = null;
		try {
			factory = DAOFactory.getInstance();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		CourseDAO courseDAO = factory.getCourseDAO();
		return courseDAO.getAllTutorsCourses(user.getId());
	}


}
