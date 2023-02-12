package ru.rsreu.electivecourses.listeners;




import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.datalayer.oracledb.DAOFactory;
import ru.rsreu.electivecourses.datalayer.oracledb.UserDAO;
import ru.rsreu.electivecourses.utils.AppUtils;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.sql.SQLException;

public class TimeoutSessionListener implements HttpSessionListener {

    private static final int MAX_INACTIVE_INTERVAL = 120 * 60;

    private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

    public TimeoutSessionListener() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();

        session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        User user = AppUtils.getLoginUser(session);

        user.set_active(false);
        userDAO.editUser(user);
    }
}
