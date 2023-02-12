package ru.rsreu.electivecourses.datalayer.oracledb;

import ru.rsreu.electivecourses.datalayer.data.Statuses;
import ru.rsreu.electivecourses.datalayer.data.StudentsCourse;
import ru.rsreu.electivecourses.datalayer.data.User;
import ru.rsreu.electivecourses.propertiesmanagers.DBConfigurationManager;
import ru.rsreu.electivecourses.propertiesmanagers.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class to access studentsCourses in DB
 */
public class StudentsCourseDAO {
    /**
     * Connection to DB
     */
    private Connection connection;

    /**
     * Constructor DAO
     * @param connection connection to DB
     */
    public StudentsCourseDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Create StudentsCourse from query
     * @param rs result of query to create course
     * @return studentsCourse
     */
    public StudentsCourse addStudentsCourse(ResultSet rs){
        StudentsCourse studentsCourse = new StudentsCourse();
        PreparedStatement ps = null;
        ResultSet rsSub = null;
        try {
            studentsCourse.setStatus(Statuses.valueOf(rs.getString(DBConfigurationManager.getProperty("table.studentscourse.status")).toUpperCase()));
            int idStudent = rs.getInt(DBConfigurationManager.getProperty("table.studentscourse.studentid"));
            ps = this.connection.prepareStatement(QueryManager.getProperty("query.finduserById"));
            ps.setInt(1, idStudent);
            rsSub = ps.executeQuery();
            if(rsSub.next()){
                UserDAO userdao = new UserDAO(connection);
                studentsCourse.setStudent(userdao.addUser(rsSub));
            }
            ps = this.connection.prepareStatement(QueryManager.getProperty("query.findCourseById"));
            int idCourse = rs.getInt(DBConfigurationManager.getProperty("table.studentscourse.idcourse"));
            ps.setInt(1, idCourse);
            rsSub = ps.executeQuery();
            if(rsSub.next()){
                CourseDAO courseDAO = new CourseDAO(connection);
                studentsCourse.setCourse(courseDAO.addCourse(rsSub));
            }
            rsSub.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentsCourse;
    }

    /**
     * Get list of all coursers for student
     * @param id id of student
     * @return list of students Courses
     */
    public List<StudentsCourse> getAllStudentsCourses(int id){
        List<StudentsCourse> allCourses = new ArrayList<StudentsCourse>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps= this.connection.prepareStatement(QueryManager.getProperty("query.getStudentCourses"));
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while(rs.next()){
                allCourses.add(addStudentsCourse(rs));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allCourses;
    }

    /**
     * Add course for student
     * @param studentId id of a student
     * @param courseId id of a course
     * @return
     */
    public int signForCourse(int studentId, int courseId){
        int res = 0;
        PreparedStatement ps = null;
        try {
            ps = this.connection.prepareStatement(QueryManager.getProperty("query.signCourse"));
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.setString(3, Statuses.LISTED.toString());
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
     * Expel student from a course
     * @param idStudent id of a student
     * @param idCourse id of a course
     * @return number of afflicted rows
     */
    public int expelStudent(int idStudent, int idCourse){
        int res =  0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps= this.connection.prepareStatement(QueryManager.getProperty("query.expelCourse"));
            ps.setInt(1, idStudent);
            ps.setInt(2, idCourse);
            res = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    /**
     * Get students of the course
     * @param idCourse id of a course
     * @return list of course students
     */
    public List<User> getCourseStudents(int idCourse){
        List<User> students = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps= this.connection.prepareStatement(QueryManager.getProperty("query.getCourseStudents"));
            ps.setInt(1, idCourse);
            rs = ps.executeQuery();
            while (rs.next()){
                UserDAO userDAO = new UserDAO(connection);
                students.add(userDAO.addUser(rs));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    /**
     * Get students without final mark for the course
     * @param idCourse id of a course
     * @return list of users without final mark for the course
     */
    public List<User> getCourseStudentsNotFinalMark(int idCourse){
        List<User> students = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps= this.connection.prepareStatement(QueryManager.getProperty("query.studentsNotFinalMark"));
            ps.setInt(1, idCourse);
            ps.setInt(2, idCourse);
            rs = ps.executeQuery();
            while (rs.next()){
                UserDAO userDAO = new UserDAO(connection);
                students.add(userDAO.addUser(rs));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }


}
