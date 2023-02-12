package ru.rsreu.electivecourses.commands.moderator;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.commands.moderator.AllUsersForModeratorCommand;
import ru.rsreu.electivecourses.datalayer.oracledb.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager.getProperty;

public class UnblockUserCommand implements ActionCommand {
    private static final String PARAM_NAME_USERID = "userID";

    public String execute(HttpServletRequest request) {
        String page = getProperty("path.page.allUsersModerator");
        String id = request.getParameter(PARAM_NAME_USERID);
        try {
            DAOFactory factory = DAOFactory.getInstance();
            factory.unblockUser(Integer.parseInt(id));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AllUsersForModeratorCommand.getAllUsers(request);
        return page;
    }
}
