package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Student;
import edu.ccrm.exception.DuplicateEnrollmentException;

public class EnrollmentServiceImpl implements EnrollmentService {

    private final DataStore dataStore = DataStore.getInstance();
    private final StudentService studentService = new StudentServiceImpl();
    private final CourseService courseService = new CourseServiceImpl();

    @Override
    public void enrollStudent(int studentId, String courseCode) throws DuplicateEnrollmentException {
        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseByCode(courseCode);

        if (student == null || course == null) {
            System.out.println("Enrollment failed: Student or Course not found.");
            return;
        }

        // Check for duplicate enrollment using a stream
        boolean alreadyEnrolled = dataStore.getEnrollments().stream()
            .anyMatch(e -> e.getStudent().getId() == studentId && e.getCourse().getCode().equalsIgnoreCase(courseCode));
        
        if (alreadyEnrolled) {
            // Throw the custom checked exception if a duplicate is found
            throw new DuplicateEnrollmentException("Student " + studentId + " is already enrolled in course " + courseCode);
        }
        
        // If no duplicate, create and add the new enrollment
        Enrollment newEnrollment = new Enrollment(student, course);
        dataStore.getEnrollments().add(newEnrollment);
        System.out.println("Enrollment successful!");
    }

    @Override
    public void assignGrade(int studentId, String courseCode, Grade grade) {
        // Find the specific enrollment record for this student and course
        for (Enrollment enrollment : dataStore.getEnrollments()) {
            if (enrollment.getStudent().getId() == studentId && enrollment.getCourse().getCode().equalsIgnoreCase(courseCode)) {
                enrollment.setGrade(grade);
                System.out.println("Grade assigned successfully.");
                return; // Exit the loop and method after finding and updating
            }
        }
        // This message will only be shown if the loop completes without finding a match
        System.out.println("Failed to assign grade: Enrollment record not found.");
    }
}