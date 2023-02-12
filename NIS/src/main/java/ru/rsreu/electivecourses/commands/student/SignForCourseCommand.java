package ru.rsreu.electivecourses.commands.student;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.datalayer.data.Course;
import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.datalayer.oracledb.CourseDAO;
import ru.rsreu.electivecourses.datalayer.oracledb.DAOFactory;
import ru.rsreu.electivecourses.datalayer.oracledb.StudentsCourseDAO;
import ru.rsreu.electivecourses.propertiesmanagers.MessageManager;
import ru.rsreu.electivecourses.utils.AppUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

import static ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager.getProperty;

public class SignForCourseCommand implements ActionCommand {
    private static final String PARAM_NAME_ID = "idSign";

    public String execute(HttpServletRequest request) {
        String page = getProperty("path.page.signCourse");
        int id = Integer.parseInt(request.getParameter(PARAM_NAME_ID));

        try {
            DAOFactory factory = DAOFactory.getInstance();
            StudentsCourseDAO studentsCourseDAO = factory.getStudentCourseDAO();
            CourseDAO courseDAO = factory.getCourseDAO();
            List<Course> allCourses = courseDAO.getAllCourses();
            request.setAttribute("coursesList", allCourses);
            User user = AppUtils.getLoginUser(request.getSession());
            if (studentsCourseDAO.signForCourse(user.getId(), id) != 0) {
                request.setAttribute("resultMessage", MessageManager.getProperty("message.success"));
            } else {
                request.setAttribute("resultMessage", MessageManager.getProperty("message.alreadySigned"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return page;
    }
}
