package ru.rsreu.electivecourses.commands.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.rsreu.electivecourses.commands.ActionCommand;
import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.datalayer.oracledb.DAOFactory;
import ru.rsreu.electivecourses.datalayer.oracledb.UserDAO;
import ru.rsreu.electivecourses.propertiesmanagers.ConfigurationManager;
import ru.rsreu.electivecourses.utils.AppUtils;

import java.sql.SQLException;

public class LogoutCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		User user = AppUtils.getLoginUser(request.getSession());
		user.set_active(false);
		DAOFactory factory = null;
		try {
			factory = DAOFactory.getInstance();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		UserDAO userDAO = factory.getUserDAO();
		userDAO.editUser(user);
		HttpSession session = request.getSession(false);
		session.invalidate();
		String page = ConfigurationManager.getProperty("path.page.index");
		return page;
	}

}
