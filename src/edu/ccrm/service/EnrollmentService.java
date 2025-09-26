package edu.ccrm.service;

import edu.ccrm.domain.Grade;
import edu.ccrm.exception.DuplicateEnrollmentException; // <-- The necessary import

public interface EnrollmentService {

    /**
     * Enrolls a student in a course.
     * @throws DuplicateEnrollmentException if the student is already enrolled in the course.
     */
    // This is the line that fixes the error. We are adding "throws DuplicateEnrollmentException".
    void enrollStudent(int studentId, String courseCode) throws DuplicateEnrollmentException;
    
    /**
     * Assigns a grade to a student for a specific course enrollment.
     */
    void assignGrade(int studentId, String courseCode, Grade grade);
}