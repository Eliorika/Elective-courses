package ru.rsreu.electivecourses.datalayer.oracledb;

import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.datalayer.data.UserType;
import ru.rsreu.electivecourses.propertiesmanagers.DBConfigurationManager;
import ru.rsreu.electivecourses.propertiesmanagers.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * DAO class to access users in DB
 */
public class UserDAO {
	/**
	 * Connection to DB
	 */
	private Connection connection;

	/**
	 * Constructor DAO
	 * @param connection connection to DB
	 */
	public UserDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Create user from query
	 * @param rs result of a query
	 * @return user of a system
	 */
	public User addUser(ResultSet rs){
		User user = new User();
		try {
				user.setName(rs.getString(DBConfigurationManager.getProperty("table.nisusers.username")));
				user.setId(rs.getInt(DBConfigurationManager.getProperty("table.nisusers.id")));
				user.setLogin(rs.getString(DBConfigurationManager.getProperty("table.nisusers.login")));
				user.setPassword(rs.getString(DBConfigurationManager.getProperty("table.nisusers.password")));
				user.set_active(rs.getBoolean(DBConfigurationManager.getProperty("table.nisusers.isactive")));
				user.set_blocked(rs.getBoolean(DBConfigurationManager.getProperty("table.nisusers.isblocked")));
				user.setType(UserType.valueOf(rs.getString(DBConfigurationManager.getProperty("table.userstype.typename")).toUpperCase()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * Login to a system
	 * @param login user's login
	 * @param password user's password
	 * @return result of logging
	 */
	public boolean logIn(String login, String password) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean res = false;
		try {
			ps= this.connection.prepareStatement(QueryManager.getProperty("query.getpassword"));
			ps.setString(1, login);
			rs = ps.executeQuery();
			String pass = null;
			if(rs.next()){
				pass = rs.getString(DBConfigurationManager.getProperty("table.nisusers.password"));
			}
			if(password.equals(pass)){
				res = true;
				ps= this.connection.prepareStatement(QueryManager.getProperty("query.getuser"));
				ps.setString(1, login);
				rs = ps.executeQuery();
				if(rs.next()){
					User.setLoggedUser(addUser(rs));
				}
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return res;
	}

	/**
	 * Get all students in the system
	 * @return list of all users
	 */
	public List<User> getAllUsers(){
		List<User> allUsers = new ArrayList<User>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st= this.connection.createStatement();
			rs = st.executeQuery(QueryManager.getProperty("query.getAllUsers"));
			while(rs.next()){
				allUsers.add(addUser(rs));
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return allUsers;
	}

	/**
	 * Create new user
	 * @param login login of a new user
	 * @param password password of a new user
	 * @param name name of a new user
	 * @param type type of new user
	 * @return number of affected rows
	 */
	public int createNewUser(String login, String password, String name, String type){
		int res = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int active = 0;
		int blocked = 0;
		try {
			ps= this.connection.prepareStatement(QueryManager.getProperty("query.getIdType"));
			ps.setString(1, type);
			rs = ps.executeQuery();
			int userType = 0;
			if(rs.next()){
				userType = rs.getInt(DBConfigurationManager.getProperty("table.userstype.id"));
			}
			rs.close();

			ps = this.connection.prepareStatement(QueryManager.getProperty("query.createUser"));
			ps.setString(1, login);
			ps.setString(2, password);
			ps.setInt(3, active);
			ps.setInt(4, blocked);
			ps.setString(5, name);
			ps.setInt(6, userType);

			res = ps.executeUpdate();
			ps.close();
		} catch (SQLIntegrityConstraintViolationException e){
			res = 0;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return res;
	}

	/**
	 * Find user in DB by login
	 * @param login login of a user
	 * @return found user
	 */
	public User findUser(String login){
		User user = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps= this.connection.prepareStatement(QueryManager.getProperty("query.findUserByLogin"));
			ps.setString(1, login);
			rs = ps.executeQuery();
			if(rs.next()){
				user = addUser(rs);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return user;
	}

	/**
	 * Delete user from DB
	 * @param id id of user to delete
	 * @return number of afflicted row
	 */
	public int deleteUser(int id){
		int res =  0;
		PreparedStatement ps = null;
		try {
			ps= this.connection.prepareStatement(QueryManager.getProperty("query.deleteUser"));
			ps.setInt(1, id);
			res = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return res;
	}

	/**
	 * Block user
	 * @param id user id
	 * @return num of afflicted rows
	 */
	public int blockUser(int id){
		int res =  0;
		PreparedStatement ps = null;
		try {
			ps= this.connection.prepareStatement(QueryManager.getProperty("query.blockUser"));
			ps.setInt(1, id);
			res = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return res;
	}

	/**
	 * Unblock user
	 * @param id user id
	 * @return number of afflicted rows
	 */
	public int unblockUser(int id){
		int res =  0;
		PreparedStatement ps = null;
		try {
			ps= this.connection.prepareStatement(QueryManager.getProperty("query.unblockUser"));
			ps.setInt(1, id);
			res = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return res;
	}

	/**
	 * Edit users info in DB
	 * @param login new login
	 * @param password new password
	 * @param name new name
	 * @param type new type
	 * @param active is user authorized
	 * @param blocked is user blocked
	 * @param id user id
	 * @return number of afflicted rows
	 */
	public int editUser(String login, String password, String name, String type, int active, int blocked, int id){
		int res = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps= this.connection.prepareStatement(QueryManager.getProperty("query.getIdType"));
			ps.setString(1, type);
			rs = ps.executeQuery();
			int userType = 0;
			if(rs.next()){
				userType = rs.getInt(DBConfigurationManager.getProperty("table.userstype.id"));
			}
			rs.close();
			ps = this.connection.prepareStatement(QueryManager.getProperty("query.editUser"));
			ps.setString(1, login);
			ps.setString(2, password);
			ps.setInt(3, blocked);
			ps.setInt(4, active);
			ps.setInt(5, userType);
			ps.setString(6, name);
			ps.setInt(7, id);
			res = ps.executeUpdate();
			ps.close();
		} catch (SQLIntegrityConstraintViolationException e){
			res = 0;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return res;
	}

	/**
	 * Edit user
	 * @param user altered user
	 * @return number of afflicted rows
	 */
	public int editUser(User user){
		int res = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps= this.connection.prepareStatement(QueryManager.getProperty("query.getIdType"));
			ps.setString(1, user.getType().toString());
			rs = ps.executeQuery();
			int userType = 0;
			if(rs.next()){
				userType = rs.getInt(DBConfigurationManager.getProperty("table.userstype.id"));
			}
			rs.close();
			ps = this.connection.prepareStatement(QueryManager.getProperty("query.editUser"));
			ps.setString(1, user.getLogin());
			ps.setString(2, user.getPassword());
			int active = user.is_active()?1:0;
			int blocked = user.is_blocked()?1:0;
			ps.setInt(3, blocked);
			ps.setInt(4, active);
			ps.setInt(5, userType);
			ps.setString(6, user.getName());
			ps.setInt(7, user.getId());
			res = ps.executeUpdate();
			ps.close();
		} catch (SQLIntegrityConstraintViolationException e){
			res = 0;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return res;
	}

}
