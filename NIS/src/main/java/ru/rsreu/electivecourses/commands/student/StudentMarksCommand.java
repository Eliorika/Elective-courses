package ru.rsreu.electivecourses.commands.student;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.datalayer.data.StudentsCourse;
import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.datalayer.oracledb.DAOFactory;
import ru.rsreu.electivecourses.datalayer.oracledb.StudentsCourseDAO;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;
import ru.rsreu.electivecourses.utils.AppUtils;

import java.sql.SQLException;
import java.util.List;

public class StudentMarksCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		request.setAttribute("listCourse", getStudentCourses(request));
		String page = ConfigurationManager.getProperty("path.page.studentMark");
		return page;
	}

	public static List<StudentsCourse> getStudentCourses(HttpServletRequest request) {
		DAOFactory factory = null;
		try {
			factory = DAOFactory.getInstance();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		User user = AppUtils.getLoginUser(request.getSession());
		StudentsCourseDAO studentCourseDAO = factory.getStudentCourseDAO();
		return studentCourseDAO.getAllStudentsCourses(user.getId());
	}

}

