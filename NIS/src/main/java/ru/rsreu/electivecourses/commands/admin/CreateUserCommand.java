package ru.rsreu.electivecourses.commands.admin;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.datalayer.oracledb.DAOFactory;
import ru.rsreu.electivecourses.propertiesmanagers.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager.getProperty;

public class CreateUserCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "txtLogin";
    private static final String PARAM_NAME_PASSWORD = "txtPassword";
    private static final String PARAM_NAME_USERNAME = "txtNameUser";
    private static final String PARAM_NAME_USERTYPE = "userType";

    public String execute(HttpServletRequest request) {
        String page = getProperty("path.page.createUser");
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String userName = request.getParameter(PARAM_NAME_USERNAME);
        String userType = request.getParameter(PARAM_NAME_USERTYPE);
        try {
            if(!(login.isEmpty() || password.isEmpty() || userName.isEmpty() || userType.isEmpty())) {
                DAOFactory factory = DAOFactory.getInstance();
                if (factory.createUser(login, password, userName, userType) != 0) {
                    request.setAttribute("resultMessage", MessageManager.getProperty("message.success"));
                } else {
                    request.setAttribute("resultMessage", MessageManager.getProperty("message.addUserFailed"));

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
