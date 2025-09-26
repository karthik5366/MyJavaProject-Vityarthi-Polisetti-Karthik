package edu.ccrm.service;

import edu.ccrm.domain.Grade;
// We will create these custom exceptions in a later phase.
// For now, just add the import statements. Eclipse will show an error, which is OK.
// import edu.ccrm.exception.CourseNotFoundException;
// import edu.ccrm.exception.StudentNotFoundException;

public interface EnrollmentService {
    // The 'throws' clause indicates that this method might fail in specific ways.
    void enrollStudent(int studentId, String courseCode); // throws StudentNotFoundException, CourseNotFoundException;
    void assignGrade(int studentId, String courseCode, Grade grade); // throws StudentNotFoundException, CourseNotFoundException;
}