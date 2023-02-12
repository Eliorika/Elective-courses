package ru.rsreu.electivecourses.datalayer.oracledb;

import ru.rsreu.electivecourses.datalayer.data.Course;
import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.propertiesmanagers.DBConfigurationManager;
import ru.rsreu.electivecourses.propertiesmanagers.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class to access courses in DB
 */
public class CourseDAO {
    /**
     * Connection to DB
     */
    private Connection connection;

    /**
     * Constructor DAO
     * @param connection connection to DB
     */
    public CourseDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Create Course from query result
     * @param rs result of query to create course
     * @return course
     */
    public Course addCourse(ResultSet rs){
        Course course = new Course();
        PreparedStatement ps = null;
        ResultSet rsUser = null;
        try {
                course.setId(rs.getInt(DBConfigurationManager.getProperty("table.courses.id")));
                course.setCourseDescription(rs.getString(DBConfigurationManager.getProperty("table.courses.coursedescription")));
                int idTutor = rs.getInt(DBConfigurationManager.getProperty("table.courses.idtutor"));
                ps = this.connection.prepareStatement(QueryManager.getProperty("query.finduserById"));
                ps.setInt(1, idTutor);
                rsUser = ps.executeQuery();
                if(rsUser.next()) {
                    UserDAO userdao = new UserDAO(connection);
                    course.setTutor(userdao.addUser(rsUser));
                }
                rsUser.close();
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    /**
     * Get all courses from the tutor
     * @param id id of a tutor
     * @return
     */
    public List<Course> getAllTutorsCourses(int id){
        List<Course> allCourses = new ArrayList<Course>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps= this.connection.prepareStatement(QueryManager.getProperty("query.getTutorCourses"));
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while(rs.next()){
                allCourses.add(addCourse(rs));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allCourses;
    }

    /**
     * Add new course to DB
     * @param idTutor id of a tutor
     * @param courseDescription description of a new course
     * @return res number of changed rows in a DB
     */
    public int createCourse(int idTutor, String courseDescription){
        int res = 0;
        PreparedStatement ps = null;
        try {
            ps= this.connection.prepareStatement(QueryManager.getProperty("query.createCourse"));
            ps.setInt(1, idTutor);
            ps.setString(2, courseDescription);
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
     * Get all courses in the system
     * @return
     */
    public List<Course> getAllCourses(){
        List<Course> allCourses = new ArrayList<Course>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st= this.connection.createStatement();
            rs = st.executeQuery(QueryManager.getProperty("query.getAllCourses"));
            while(rs.next()){
                allCourses.add(addCourse(rs));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allCourses;
    }


}
