package ru.rsreu.electivecourses.commands.student;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;

import static ru.rsreu.electivecourses.commands.student.StudentMarksCommand.getStudentCourses;

public class StudentAttendanceCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		request.setAttribute("listCourse", getStudentCourses(request));
		String page = ConfigurationManager.getProperty("path.page.studentAttendance");
		return page;
	}

}
