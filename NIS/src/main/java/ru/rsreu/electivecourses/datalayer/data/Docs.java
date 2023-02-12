package ru.rsreu.electivecourses.datalayer.data;

import lombok.Data;

import java.util.Date;

/**
 * Data class for docs of a course. Stores information about grades and attendances
 */
public @Data class Docs {
    /**
     * Student of a course. See {@link ru.rsreu.electivecourses.datalayer.data.User} for details
     */
    User student;
    /**
     * Course the doc references to. See {@link ru.rsreu.electivecourses.datalayer.data.Course} for details
     */
    Course course;
    /**
     * Date of lesson
     */
    Date date;
    /**
     * Attendance of a student
     */
    boolean attendance;
    /**
     * Mark for this specific lesson
     */
    int mark;
}
