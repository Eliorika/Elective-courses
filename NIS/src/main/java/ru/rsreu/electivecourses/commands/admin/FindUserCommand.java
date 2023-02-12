package ru.rsreu.electivecourses.commands.admin;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.datalayer.oracledb.DAOFactory;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;
import ru.rsreu.electivecourses.propertiesmanagers.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager.getProperty;

public class FindUserCommand implements ActionCommand {
    private static final String PARAM_NAME_SEARCH = "txtSearch";
    private static final String PARAM_NAME_TYPE = "usertype";
    private static final String PARAM_NAME_TYPE_ADMIN = "admin";
    private static final String PARAM_NAME_TYPE_MODERATOR = "moderator";

    public String execute(HttpServletRequest request) {
        String page = null;
        String search = request.getParameter(PARAM_NAME_SEARCH);
        String type = request.getParameter(PARAM_NAME_TYPE);
        if(PARAM_NAME_TYPE_ADMIN.equals(type)){
            page = ConfigurationManager.getProperty("path.page.adminFindUsers");
        } else if(PARAM_NAME_TYPE_MODERATOR.equals(type)){
            page = ConfigurationManager.getProperty("path.page.findUserModerator");
        }
        User user = null;
        try {
            if(!search.isEmpty()) {
                DAOFactory factory = DAOFactory.getInstance();
                user = factory.findUser(search);
                if (factory.findUser(search) == null) {
                    request.setAttribute("resultMessage", MessageManager.getProperty("message.findUserFailed"));
                }
                else {
                    request.setAttribute("user", user);
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
