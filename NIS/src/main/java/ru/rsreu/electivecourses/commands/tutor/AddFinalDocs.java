package ru.rsreu.electivecourses.commands.tutor;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.datalayer.oracledb.DAOFactory;
import ru.rsreu.electivecourses.datalayer.oracledb.DocsDAO;
import ru.rsreu.electivecourses.datalayer.oracledb.FinalDocsDAO;
import ru.rsreu.electivecourses.propertiesmanagers.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ru.rsreu.electivecourses.commands.tutor.DocsCommand.getTutorCourses;
import static ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager.getProperty;

public class AddFinalDocs implements ActionCommand {
    private static final String PARAM_NAME_COURSEID = "course";
    private static final String PARAM_NAME_STUDENTID = "student";
    private static final String PARAM_NAME_MARK = "finalMark";

    public String execute(HttpServletRequest request) {
        String page = getProperty("path.page.finalDocs");
        String courseID = request.getParameter(PARAM_NAME_COURSEID);
        String studentID = request.getParameter(PARAM_NAME_STUDENTID);
        String mark = request.getParameter(PARAM_NAME_MARK);
        try {
            DAOFactory factory = DAOFactory.getInstance();
            FinalDocsDAO finaldocsDAO = factory.getFinalDocsDAO();
            request.setAttribute("listCourse", getTutorCourses(request));
            request.setAttribute("courseID", courseID);
            request.setAttribute("docs", factory.getDocsDAO().getStudentMarks(Integer.parseInt(studentID), Integer.parseInt(courseID)));
            request.setAttribute("studentID", studentID);
            request.setAttribute("showMark", true);
                if (finaldocsDAO.addFinalMark(Integer.parseInt(studentID), Integer.parseInt(courseID), mark) != 0) {
                    request.setAttribute("resultMessage", MessageManager.getProperty("message.success"));
                    request.setAttribute("docs", factory.getDocsDAO().getStudentMarks(Integer.parseInt(studentID), Integer.parseInt(courseID)));
                }
            request.setAttribute("listStudents", factory.getStudentCourseDAO().getCourseStudentsNotFinalMark(Integer.parseInt(courseID)));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return page;
    }
}
