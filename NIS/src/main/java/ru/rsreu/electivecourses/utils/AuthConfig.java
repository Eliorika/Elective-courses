package ru.rsreu.electivecourses.utils;

import ru.rsreu.electivecourses.commands.CommandEnum;
import ru.rsreu.electivecourses.datalayer.data.UserType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AuthConfig {

    private static final Map<UserType, ArrayList<CommandEnum>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {

        ArrayList<CommandEnum> tutorCommands = new ArrayList<>();
        tutorCommands.add(CommandEnum.LOGOUT);
        tutorCommands.add(CommandEnum.GOTONEWCOURSE);
        tutorCommands.add(CommandEnum.GOTOTUTORCOURSES);
        tutorCommands.add(CommandEnum.GOTODOCS);
        tutorCommands.add(CommandEnum.GOTOFINALDOCS);
        tutorCommands.add(CommandEnum.GOTOTUTORPROFILE);
        tutorCommands.add(CommandEnum.CREATECOURSE);
        tutorCommands.add(CommandEnum.GETSTUDENTSOFCOURSE);
        tutorCommands.add(CommandEnum.GETFINALMARKS);
        tutorCommands.add(CommandEnum.GETSTUDENTSDOCS);
        tutorCommands.add(CommandEnum.ADDDOCS);
        tutorCommands.add(CommandEnum.ADDFINALDOCS);
        mapConfig.put(UserType.TUTOR, tutorCommands);

        ArrayList<CommandEnum> studentsCommands = new ArrayList<>();
        studentsCommands.add(CommandEnum.LOGOUT);
        studentsCommands.add(CommandEnum.GOTOSTUDENTCOURSES);
        studentsCommands.add(CommandEnum.GOTOSIGNCOURSE);
        studentsCommands.add(CommandEnum.GOTOSTUDENTMARKS);
        studentsCommands.add(CommandEnum.GOTOSTUDENTATTENDANCE);
        studentsCommands.add(CommandEnum.GOTOSTUDENTPROFILE);
        studentsCommands.add(CommandEnum.SIGNCOURSE);
        studentsCommands.add(CommandEnum.EXPELCOURSE);
        studentsCommands.add(CommandEnum.GETMARKS);
        studentsCommands.add(CommandEnum.GETSTUDENTSOFCOURSE);
        studentsCommands.add(CommandEnum.GETFINALMARKS);

        mapConfig.put(UserType.STUDENT, studentsCommands);

        ArrayList<CommandEnum> moderatorCommands = new ArrayList<>();
        moderatorCommands.add(CommandEnum.LOGOUT);
        moderatorCommands.add(CommandEnum.GOTOMODERATORPROFILE);
        moderatorCommands.add(CommandEnum.GOTOALLUSERSMODERATOR);
        moderatorCommands.add(CommandEnum.GOTOMODERATORFINDUSER);
        moderatorCommands.add(CommandEnum.FINDUSER);
        moderatorCommands.add(CommandEnum.BLOCKUSER);
        moderatorCommands.add(CommandEnum.UNBLOCKUSER);


        mapConfig.put(UserType.MODERATOR, moderatorCommands);

        ArrayList<CommandEnum> adminCommands = new ArrayList<>();
        adminCommands.add(CommandEnum.LOGOUT);
        adminCommands.add(CommandEnum.GOTOADMINFINDUSER);
        adminCommands.add(CommandEnum.GOTOADMINPROFILE);
        adminCommands.add(CommandEnum.GOTOCREATEUSER);
        adminCommands.add(CommandEnum.GOTOALLUSERSFORADMIN);
        adminCommands.add(CommandEnum.CREATEUSER);
        adminCommands.add(CommandEnum.FINDUSER);
        adminCommands.add(CommandEnum.DELETEUSER);
        adminCommands.add(CommandEnum.EDITUSER);


        mapConfig.put(UserType.ADMIN, adminCommands);
    }

    public static Set<UserType> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static ArrayList<CommandEnum> getCommandsForRole(UserType type) {
        return mapConfig.get(type);
    }
}
