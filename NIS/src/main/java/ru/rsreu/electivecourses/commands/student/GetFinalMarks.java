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

public class GetFinalMarks implements ActionCommand {
    private static final String PARAM_NAME_COURSE = "course";
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        int courseID = Integer.parseInt(request.getParameter(PARAM_NAME_COURSE));
        try {
            DAOFactory factory = DAOFactory.getInstance();
            User user = AppUtils.getLoginUser(request.getSession());
            if(user.getType().equals(UserType.STUDENT)) {
                page = ConfigurationManager.getProperty("path.page.studentAttendance");
                request.setAttribute("listCourse", getStudentCourses(request));
            }else if (user.getType().equals(UserType.TUTOR)) {
                page = ConfigurationManager.getProperty("path.page.finalDocs");
                request.setAttribute("listCourse", getTutorCourses(request));
                List<User> students = factory.getStudentCourseDAO().getCourseStudentsNotFinalMark(courseID);
                request.setAttribute("listStudents", students);
            }

            request.setAttribute("courseID", courseID);
            request.setAttribute("marks", factory.getFinalDocsDAO().getCourseFinals(courseID));
            request.setAttribute("showMarks", true);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return page;
    }
}
