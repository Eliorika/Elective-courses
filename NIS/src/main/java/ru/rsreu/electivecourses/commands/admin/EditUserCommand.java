package ru.rsreu.electivecourses.commands.admin;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.datalayer.oracledb.DAOFactory;
import ru.rsreu.electivecourses.propertiesmanagers.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager.getProperty;

public class EditUserCommand implements ActionCommand {
    private static final String PARAM_NAME_ID = "idEdit";
    private static final String PARAM_NAME_LOGIN = "txtLogin";
    private static final String PARAM_NAME_PASSWORD = "txtPassword";
    private static final String PARAM_NAME_USERNAME = "txtNameUser";
    private static final String PARAM_NAME_USERTYPE = "userType";
    private static final String PARAM_NAME_BLOCKED = "isBlocked";
    private static final String PARAM_NAME_ACTIVE = "isActive";
    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter(PARAM_NAME_ID));
        String page = getProperty("path.page.adminFindUsers");
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String userName = request.getParameter(PARAM_NAME_USERNAME);
        String userType = request.getParameter(PARAM_NAME_USERTYPE);
        int is_active = 0;
        int is_blocked = Integer.parseInt(request.getParameter(PARAM_NAME_BLOCKED));


        try {
            if(!(login.isEmpty() || password.isEmpty() || userName.isEmpty() || userType.isEmpty())) {
                DAOFactory factory = DAOFactory.getInstance();
                if (factory.editUser(login, password, userName, userType, is_active, is_blocked, id) != 0) {
                    request.setAttribute("resultMessageEdit", MessageManager.getProperty("message.success"));
                } else {
                    request.setAttribute("resultMessageEdit", MessageManager.getProperty("message.addUserFailed"));

                }
            } else {
                request.setAttribute("resultMessageEdit", MessageManager.getProperty("message.emptyFields"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return page;
    }
}
