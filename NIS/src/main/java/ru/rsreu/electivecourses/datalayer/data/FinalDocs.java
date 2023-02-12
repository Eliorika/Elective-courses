package ru.rsreu.electivecourses.datalayer.data;

import lombok.Data;

/**
 * Data class for final marks for the course. Stores information about final marks
 */
public @Data class FinalDocs {
    /**
     * Student of a course. See {@link ru.rsreu.electivecourses.datalayer.data.User} for details
     */
    User student;
    /**
     * Course the final doc references to. See {@link ru.rsreu.electivecourses.datalayer.data.Course} for details
     */
    Course course;
    /**
     * Final mark for the course
     */
    String mark;
}
