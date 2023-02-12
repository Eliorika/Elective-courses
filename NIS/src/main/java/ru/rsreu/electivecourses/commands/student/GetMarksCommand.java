package ru.rsreu.electivecourses.commands.student;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.datalayer.data.Docs;
import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.datalayer.oracledb.DAOFactory;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;
import ru.rsreu.electivecourses.utils.AppUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

import static ru.rsreu.electivecourses.commands.student.StudentMarksCommand.getStudentCourses;

public class GetMarksCommand implements ActionCommand {

    private static final String PARAM_NAME_COURSE = "course";
    private static final String PARAM_NAME_STUDENT = "student";
    @Override
    public String execute(HttpServletRequest request) {
        Integer student = null;
        String page = ConfigurationManager.getProperty("path.page.studentMark");
        int courseID = Integer.parseInt(request.getParameter(PARAM_NAME_COURSE));
        String studentS = request.getParameter(PARAM_NAME_STUDENT);
        if (studentS == null){
            User user = AppUtils.getLoginUser(request.getSession());
            student = user.getId();
        } else {
            student = Integer.parseInt(studentS);
        }
        try {
            DAOFactory factory = DAOFactory.getInstance();
            List<Docs> docs = factory.getDocsDAO().getStudentMarks(student, courseID);
            request.setAttribute("listStudents", factory.getStudentCourseDAO().getCourseStudents(courseID));
            request.setAttribute("showStudentsList", true);
            request.setAttribute("listCourse", getStudentCourses(request));
            request.setAttribute("courseID", courseID);
            request.setAttribute("docs", docs);
            request.setAttribute("showDocs", true);
            request.setAttribute("studentID", student);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return page;
    }
}
