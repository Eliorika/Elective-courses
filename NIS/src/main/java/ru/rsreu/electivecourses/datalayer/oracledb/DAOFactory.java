package ru.rsreu.electivecourses.datalayer.oracledb;

import oracle.jdbc.OracleDriver;
import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.datalayer.data.UserType;
import ru.rsreu.electivecourses.propertiesmanagers.DBConfigurationManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

/**
 * Facttory to creat DAO
 */
public class DAOFactory {
	/**
	 * Existed instance of factory
	 */
	private static volatile DAOFactory instance;
	/**
	 * Connection to DB
	 */
	private Connection connection;

	/**
	 * Constructor of factory
	 */
	private DAOFactory() {
	}

	/**
	 * Get instance of factory
	 * @return factory
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static DAOFactory getInstance()
			throws ClassNotFoundException, SQLException {
		DAOFactory factory = instance;
		synchronized (DAOFactory.class) {
			if (instance == null) {
				instance = factory = new DAOFactory();
				factory.connected();
			}
		}
		return factory;
	}

	private void connected() throws ClassNotFoundException, SQLException {
		//Locale.setDefault(Locale.ENGLISH);

		String url = DBConfigurationManager.getProperty("data.url");
		String user = DBConfigurationManager.getProperty("data.user");
		String password = DBConfigurationManager.getProperty("data.password");
		DriverManager.registerDriver(new OracleDriver());
		connection = DriverManager.getConnection(url, user, password);
		System.out.println("Connected to oracle DB!");
	}



	public boolean login(String login, String password){
		UserDAO userDAO = new UserDAO(connection);
		return userDAO.logIn(login, password);
	}

	public List<User> getAllUsers(){
		UserDAO userDAO = new UserDAO(connection);
		return userDAO.getAllUsers();
	}

	public int createUser(String login, String password, String name, String type){
		UserDAO userDAO = new UserDAO(connection);
		return userDAO.createNewUser(login, password, name, type);
	}

	public User findUser(String login){
		UserDAO userDAO = new UserDAO(connection);
		return userDAO.findUser(login);
	}

	public int deleteUser(int id){
		UserDAO userDAO = new UserDAO(connection);
		return userDAO.deleteUser(id);
	}

	public int blockUser(int id){
		UserDAO userDAO = new UserDAO(connection);
		return userDAO.blockUser(id);
	}

	public int unblockUser(int id){
		UserDAO userDAO = new UserDAO(connection);
		return userDAO.unblockUser(id);
	}

	public int editUser(String login, String password, String name, String type, int active, int blocked, int id){
		UserDAO userDAO = new UserDAO(connection);
		return userDAO.editUser(login, password, name, type, active, blocked, id);
	}

	public UserDAO getUserDAO(){
		return new UserDAO(connection);
	}
	public CourseDAO getCourseDAO(){return new CourseDAO(connection);}
	public StudentsCourseDAO getStudentCourseDAO(){return new StudentsCourseDAO(connection);}

	public DocsDAO getDocsDAO(){return new DocsDAO(connection);}

	public FinalDocsDAO getFinalDocsDAO(){return new FinalDocsDAO(connection);}
//	@Override
//	public UserDAO getUserDAO() {
//		return null;//new OracleUserDAO(connection);
//	}

}
