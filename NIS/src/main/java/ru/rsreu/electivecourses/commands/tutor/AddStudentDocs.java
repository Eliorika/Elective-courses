package ru.rsreu.electivecourses.commands.tutor;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.datalayer.oracledb.DAOFactory;
import ru.rsreu.electivecourses.datalayer.oracledb.DocsDAO;
import ru.rsreu.electivecourses.propertiesmanagers.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ru.rsreu.electivecourses.commands.tutor.DocsCommand.getTutorCourses;
import static ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager.getProperty;

public class AddStudentDocs implements ActionCommand {
    private static final String PARAM_NAME_COURSEID = "course";
    private static final String PARAM_NAME_STUDENTID = "student";
    private static final String PARAM_NAME_DATE = "date";
    private static final String PARAM_NAME_ATTENDANCE = "attendance";
    private static final String PARAM_NAME_MARK = "mark";

    public String execute(HttpServletRequest request) {
        String page = getProperty("path.page.docs");
        String courseID = request.getParameter(PARAM_NAME_COURSEID);
        String studentID = request.getParameter(PARAM_NAME_STUDENTID);
        String date = request.getParameter(PARAM_NAME_DATE);
        String attendance = request.getParameter(PARAM_NAME_ATTENDANCE);
        String mark = request.getParameter(PARAM_NAME_MARK);
        try {
            DAOFactory factory = DAOFactory.getInstance();
            DocsDAO docsDAO = factory.getDocsDAO();

            request.setAttribute("listStudents", factory.getStudentCourseDAO().getCourseStudents(Integer.parseInt(courseID)));
            request.setAttribute("showStudentsList", true);
            request.setAttribute("listCourse", getTutorCourses(request));
            request.setAttribute("courseID", courseID);
            request.setAttribute("docs", factory.getDocsDAO().getStudentMarks(Integer.parseInt(studentID), Integer.parseInt(courseID)));
            request.setAttribute("showDocs", true);
            request.setAttribute("studentID", studentID);

            if (!(date.isEmpty())) {
                if (docsDAO.addStudentDocs(Integer.parseInt(studentID), Integer.parseInt(courseID), date, mark, Integer.parseInt(attendance)) != 0) {
                    request.setAttribute("resultMessage", MessageManager.getProperty("message.success"));
                    request.setAttribute("docs", factory.getDocsDAO().getStudentMarks(Integer.parseInt(studentID), Integer.parseInt(courseID)));
                } else {
                    request.setAttribute("resultMessage", MessageManager.getProperty("message.addDocsFailed"));
                    request.setAttribute(PARAM_NAME_COURSEID, courseID);
                    request.setAttribute(PARAM_NAME_STUDENTID, studentID);
                    request.setAttribute(PARAM_NAME_DATE, date);
                    request.setAttribute(PARAM_NAME_ATTENDANCE, attendance);
                    request.setAttribute(PARAM_NAME_MARK, mark);
                }
            } else {
                request.setAttribute("resultMessage", MessageManager.getProperty("message.emptyFields"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return page;
    }
}
