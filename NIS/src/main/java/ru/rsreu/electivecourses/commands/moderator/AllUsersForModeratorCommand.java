package ru.rsreu.electivecourses.commands.moderator;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.datalayer.oracledb.DAOFactory;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AllUsersForModeratorCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        getAllUsers(request);
        String page = ConfigurationManager.getProperty("path.page.allUsersModerator");
        return page;
    }

    public static void getAllUsers(HttpServletRequest request) {
        List<User> allUsers;
        try {
            DAOFactory factory = DAOFactory.getInstance();
            allUsers = factory.getAllUsers();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("usersList", allUsers);
    }


}
