package ru.rsreu.electivecourses.commands.tutor;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.datalayer.oracledb.CourseDAO;
import ru.rsreu.electivecourses.datalayer.oracledb.DAOFactory;
import ru.rsreu.electivecourses.propertiesmanagers.MessageManager;
import ru.rsreu.electivecourses.utils.AppUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager.getProperty;

public class CreateCourseCommand implements ActionCommand {
    private static final String PARAM_NAME_DESCRIPTION = "txtDescriptionCourse";


    public String execute(HttpServletRequest request) {
        String page = getProperty("path.page.createCourse");
        String description = request.getParameter(PARAM_NAME_DESCRIPTION);
        try {
            if(!(description.isEmpty())) {
                DAOFactory factory = DAOFactory.getInstance();
                CourseDAO courseDAO = factory.getCourseDAO();
                User user = AppUtils.getLoginUser(request.getSession());
                if (courseDAO.createCourse(user.getId(), description) != 0) {
                    request.setAttribute("resultMessage", MessageManager.getProperty("message.success"));
                } else {
                    request.setAttribute("resultMessage", MessageManager.getProperty("message.inUseError"));

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
