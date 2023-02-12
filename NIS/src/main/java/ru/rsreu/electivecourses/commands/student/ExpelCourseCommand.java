package ru.rsreu.electivecourses.commands.student;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.datalayer.data.StudentsCourse;
import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.datalayer.oracledb.DAOFactory;
import ru.rsreu.electivecourses.datalayer.oracledb.StudentsCourseDAO;
import ru.rsreu.electivecourses.datalayer.oracledb.UserDAO;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;
import ru.rsreu.electivecourses.utils.AppUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

import static ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager.getProperty;

public class ExpelCourseCommand implements ActionCommand {
    private static final String PARAM_NAME_ID = "idCourse";

    public String execute(HttpServletRequest request) {
        String page = getProperty("path.page.studentCourses");
        int id = Integer.parseInt(request.getParameter(PARAM_NAME_ID));
        try {
            DAOFactory factory = DAOFactory.getInstance();
            StudentsCourseDAO studentsCourseDAO = factory.getStudentCourseDAO();
            User user = AppUtils.getLoginUser(request.getSession());
            studentsCourseDAO.expelStudent(user.getId(), id);
            StudentsCourseDAO studentcourseDAO = factory.getStudentCourseDAO();
            List<StudentsCourse> courses = studentcourseDAO.getAllStudentsCourses(user.getId());
            request.setAttribute("coursesList", courses);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return page;
    }
}
