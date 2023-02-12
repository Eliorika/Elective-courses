package ru.rsreu.electivecourses.datalayer.oracledb;

import ru.rsreu.electivecourses.datalayer.data.FinalDocs;
import ru.rsreu.electivecourses.datalayer.data.Statuses;
import ru.rsreu.electivecourses.propertiesmanagers.DBConfigurationManager;
import ru.rsreu.electivecourses.propertiesmanagers.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class to access final docs in DB
 */
public class FinalDocsDAO {
    /**
     * Connection to DB
     */
    private Connection connection;

    /**
     * Constructor DAO
     * @param connection connection to DB
     */
    public FinalDocsDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * result of query to create final doc
     * @param rs result of query to create final doc
     * @return final docs
     */
    public FinalDocs addFinalDocs(ResultSet rs){
        FinalDocs finalDocs = new FinalDocs();
        PreparedStatement ps = null;
        ResultSet rsSub = null;
        try {
            finalDocs.setMark(rs.getString(DBConfigurationManager.getProperty("table.finaldocs.mark")));
            int idStudent = rs.getInt(DBConfigurationManager.getProperty("table.finaldocs.studentid"));
            ps = this.connection.prepareStatement(QueryManager.getProperty("query.finduserById"));
            ps.setInt(1, idStudent);
            rsSub = ps.executeQuery();
            if(rsSub.next()){
                UserDAO userdao = new UserDAO(connection);
                finalDocs.setStudent(userdao.addUser(rsSub));
            }
            ps = this.connection.prepareStatement(QueryManager.getProperty("query.findCourseById"));
            int idCourse = rs.getInt(DBConfigurationManager.getProperty("table.finaldocs.idcourse"));
            ps.setInt(1, idCourse);
            rsSub = ps.executeQuery();
            if(rsSub.next()){
                CourseDAO courseDAO = new CourseDAO(connection);
                finalDocs.setCourse(courseDAO.addCourse(rsSub));
            }
            rsSub.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return finalDocs;
    }

    /**
     * Get list of final docs for course
     * @param courseID id of course
     * @return list of final docs for course
     */
    public List<FinalDocs> getCourseFinals(int courseID){
        List<FinalDocs> courseFinals = new ArrayList<FinalDocs>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps= this.connection.prepareStatement(QueryManager.getProperty("query.getCourseFinals"));
            ps.setInt(1,courseID);
            rs = ps.executeQuery();
            while(rs.next()){
                courseFinals.add(addFinalDocs(rs));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseFinals;
    }

    /**
     * Add final mark to DB
     * @param studentId id of student
     * @param courseId id of course
     * @param finalMark final mark
     * @return number of afflicted rows
     */
    public int addFinalMark(int studentId, int courseId, String finalMark){
        int res = 0;
        PreparedStatement ps = null;
        try {
            ps = this.connection.prepareStatement(QueryManager.getProperty("query.addFinalMark"));
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.setString(3, finalMark);
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
