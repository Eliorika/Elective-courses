package ru.rsreu.electivecourses.datalayer.oracledb;

import ru.rsreu.electivecourses.datalayer.data.Docs;
import ru.rsreu.electivecourses.datalayer.data.Statuses;
import ru.rsreu.electivecourses.propertiesmanagers.DBConfigurationManager;
import ru.rsreu.electivecourses.propertiesmanagers.QueryManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class to access docs in DB
 */
public class DocsDAO {
    /**
     * Connection to DB
     */
    private Connection connection;

    /**
     * Constructor DAO
     * @param connection connection to DB
     */
    public DocsDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Create course from query result
     * @param rs result of query to create course
     * @return docs
     */
    public Docs addDoc(ResultSet rs){
        Docs doc = new Docs();
        PreparedStatement ps = null;
        ResultSet rsSub = null;
        try {
            doc.setAttendance(rs.getBoolean(DBConfigurationManager.getProperty("table.docs.idAttendance")));
            doc.setMark(rs.getInt(DBConfigurationManager.getProperty("table.docs.idMark")));
            doc.setDate(rs.getDate(DBConfigurationManager.getProperty("table.lesson.attendanceDAte")));
            int idStudent = rs.getInt(DBConfigurationManager.getProperty("table.docs.idstudent"));
            ps = this.connection.prepareStatement(QueryManager.getProperty("query.finduserById"));
            ps.setInt(1, idStudent);
            rsSub = ps.executeQuery();
            if(rsSub.next()){
                UserDAO userdao = new UserDAO(connection);
                doc.setStudent(userdao.addUser(rsSub));
            }

            int idCourse = rs.getInt(DBConfigurationManager.getProperty("table.lesson.idCourse"));
            ps = this.connection.prepareStatement(QueryManager.getProperty("query.findCourseById"));
            ps.setInt(1, idCourse);
            rsSub = ps.executeQuery();
            if(rsSub.next()) {
                CourseDAO courseDAO = new CourseDAO(connection);
                doc.setCourse(courseDAO.addCourse(rsSub));
            }
            rsSub.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * Get list of Docs from course for student
     * @param studentId id of student
     * @param courseId id of course
     * @return List of docs from course for student
     */
    public List<Docs> getStudentMarks(int studentId, int courseId){
        List<Docs> marks = new ArrayList<Docs>();
        PreparedStatement ps = null;
        try {
            ps = this.connection.prepareStatement(QueryManager.getProperty("query.getStudentDocs"));
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                marks.add(addDoc(rs));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return marks;
    }

    /**
     * Add new doc to DB
     * @param studentID id of student
     * @param courseID id of course
     * @param date date of lesson
     * @param mark mark for lesson
     * @param attendance lesson's attendance
     * @return number of afflicted rows
     */
    public int addStudentDocs(int studentID, int courseID, String date, String mark, int attendance){
        int res = 0;
        PreparedStatement ps = null;
        try {
            ps = this.connection.prepareStatement(QueryManager.getProperty("query.addDoc"));
            ps.setInt(1, studentID);
            ps.setInt(2, courseID);
            ps.setDate(3, java.sql.Date.valueOf(date));
            ps.setInt(4, attendance);
            int markNum = Integer.parseInt(mark);
            if(markNum != 0){
                ps.setInt(5, markNum);
            } else{
                ps.setNull(5, java.sql.Types.INTEGER);
            }
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
