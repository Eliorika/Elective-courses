package ru.rsreu.electivecourses.datalayer.data;

import lombok.Data;

/**
 * Data class for table of students and courses they are signed for
 */

public @Data class StudentsCourse {
    /**
     * Course the table references to. See {@link ru.rsreu.electivecourses.datalayer.data.Course} for details
     */
    Course course;
    /**
     * Student of a course. See {@link ru.rsreu.electivecourses.datalayer.data.User} for details
     */
    User student;
    /**
     * Status of a student on the course. See {@link ru.rsreu.electivecourses.datalayer.data.Statuses} for details
     */
    Statuses status;

    /**
     * Get the description of a course
     * @return description of a course
     */
    public String getCourseDescription(){
        return course.getCourseDescription();
    }

    /**
     * Get name of tutor of a course
     * @return name of a tutor of a course
     */
    public String getTutor(){
        return course.getTutor().getName();
    }

    /**
     * Get id of a course
     * @return id of a course
     */
    public int getCourseId(){
        return course.getId();
    }
}
