package ru.rsreu.electivecourses.utils;



import ru.rsreu.electivecourses.commands.CommandEnum;
import ru.rsreu.electivecourses.datalayer.data.UserType;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Set;

public class AuthUtils {

    public static boolean isExistsPage(HttpServletRequest request) {
        String action = request.getParameter("command");

        Set<UserType> userTypeSet = AuthConfig.getAllAppRoles();

        for (UserType userType : userTypeSet) {
            ArrayList<CommandEnum> actionCommands = AuthConfig.getCommandsForRole(userType);
            if (actionCommands != null && actionCommands.contains(CommandEnum.valueOf(action.toUpperCase()))) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasPermission(HttpServletRequest request) {
        String action = request.getParameter("command");

        Set<UserType> allTypes = AuthConfig.getAllAppRoles();

        for (UserType userType : allTypes) {

            if (!request.isUserInRole(userType.toString())) {
                continue;
            }
            ArrayList<CommandEnum> commands = AuthConfig.getCommandsForRole(userType);

            if (commands != null && commands.contains(CommandEnum.valueOf(action.toUpperCase()))) {
                return true;
            }
        }
        return false;
    }

}
