package ru.rsreu.electivecourses.commands.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.datalayer.data.UserType;
import ru.rsreu.electivecourses.datalayer.oracledb.DAOFactory;
import ru.rsreu.electivecourses.datalayer.oracledb.UserDAO;
import ru.rsreu.electivecourses.propertiesmanagers.MessageManager;
import ru.rsreu.electivecourses.utils.AppUtils;

import java.sql.SQLException;

import static ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager.*;

public class LoginCommand implements ActionCommand {
	private static final String PARAM_NAME_LOGIN = "txtLogin";
	private static final String PARAM_NAME_PASSWORD = "txtPassword";


	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		String login = request.getParameter(PARAM_NAME_LOGIN);
		String password = request.getParameter(PARAM_NAME_PASSWORD);
		try {
			DAOFactory factory = DAOFactory.getInstance();
			if (factory.login(login, password)) {
				User user = User.getLoggedUser();
				if (!user.is_blocked()) {
					HttpSession session = request.getSession();
					user.set_active(true);
					UserDAO userDAO = factory.getUserDAO();
					userDAO.editUser(user);
					AppUtils.storeLoginUser(session, user);

					request.setAttribute("type", user.getType());
					request.setAttribute("name", user.getName());
					if (UserType.STUDENT.equals(user.getType())) {
						page = getProperty("path.page.studentProfile");
					} else if (UserType.TUTOR.equals(user.getType())) {
						page = getProperty("path.page.tutorProfile");
					} else if (UserType.ADMIN.equals(user.getType())) {
						page = getProperty("path.page.adminProfile");
					} else if (UserType.MODERATOR.equals(user.getType())) {
						page = getProperty("path.page.moderatorProfile");
					}
				} else{
					request.setAttribute("errorLoginpassMessage", MessageManager.getProperty("message.blocked"));
					page = getProperty("path.page.login");
				}
			} else {
				request.setAttribute("errorLoginpassMessage", MessageManager.getProperty("message.loginerror"));
				page = getProperty("path.page.login");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return page;
	}

}
