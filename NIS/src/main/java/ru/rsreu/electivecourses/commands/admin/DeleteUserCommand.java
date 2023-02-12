package ru.rsreu.electivecourses.commands.admin;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.commands.moderator.AllUsersForModeratorCommand;
import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.datalayer.oracledb.DAOFactory;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;
import ru.rsreu.electivecourses.utils.AppUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class DeleteUserCommand implements ActionCommand {
    private static final String PARAM_NAME_ID = "idDelete";

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter(PARAM_NAME_ID);
        try {
            User user = AppUtils.getLoginUser(request.getSession());
            if (user.getId() != Integer.parseInt(id)){
            DAOFactory factory = DAOFactory.getInstance();
            factory.deleteUser(Integer.parseInt(id));}
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AllUsersForModeratorCommand.getAllUsers(request);
        String page = ConfigurationManager.getProperty("path.page.allUsersAdmin");
        return page;
    }
}
