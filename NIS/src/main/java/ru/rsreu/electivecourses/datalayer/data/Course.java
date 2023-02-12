package ru.rsreu.electivecourses.datalayer.data;

import lombok.Data;

/**
 * Data class for Coursers.
 * Course consists of id, tutor and its description
 */
public @Data class Course {

    /**
     * Identification number of a course
     */
    int id;
    /**
     * Tutor of a course. See {@link ru.rsreu.electivecourses.datalayer.data.User} for details
     */
    User tutor;
    /**
     * Description of the course subject
     */
    String courseDescription;

}
