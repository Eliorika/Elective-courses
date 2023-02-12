package ru.rsreu.electivecourses.commands.student;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.datalayer.data.UserType;
import ru.rsreu.electivecourses.datalayer.oracledb.DAOFactory;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;
import ru.rsreu.electivecourses.utils.AppUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

import static ru.rsreu.electivecourses.commands.student.StudentMarksCommand.getStudentCourses;
import static ru.rsreu.electivecourses.commands.tutor.DocsCommand.getTutorCourses;

public class GetCourseStudents implements ActionCommand {
    private static final String PARAM_NAME_COURSE = "course";
    @Override
    public String execute(HttpServletRequest request) {
        User user = AppUtils.getLoginUser(request.getSession());
        String page = null;
        if(user.getType().equals(UserType.STUDENT)) {
            page = ConfigurationManager.getProperty("path.page.studentMark");
            request.setAttribute("listCourse", getStudentCourses(request));
        }else if (user.getType().equals(UserType.TUTOR)) {
            page = ConfigurationManager.getProperty("path.page.docs");
            request.setAttribute("listCourse", getTutorCourses(request));
        }
        int courseID = Integer.parseInt(request.getParameter(PARAM_NAME_COURSE));
        try {
            DAOFactory factory = DAOFactory.getInstance();
            List<User> students = factory.getStudentCourseDAO().getCourseStudents(courseID);
            request.setAttribute("listStudents", students);
            request.setAttribute("showStudentsList", true);
            request.setAttribute("courseID", courseID);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return page;
    }
}
