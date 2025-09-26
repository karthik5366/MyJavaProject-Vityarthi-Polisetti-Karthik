package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Student;

public class EnrollmentServiceImpl implements EnrollmentService {
    private final DataStore dataStore = DataStore.getInstance();
    // We need other services to find students and courses
    private final StudentService studentService = new StudentServiceImpl();
    private final CourseService courseService = new CourseServiceImpl();

 // Change the interface EnrollmentService first
 // void enrollStudent(int studentId, String courseCode) throws DuplicateEnrollmentException;

 // Then update the implementation in EnrollmentServiceImpl
 @Override
 public void enrollStudent(int studentId, String courseCode) throws DuplicateEnrollmentException {
     Student student = studentService.findStudentById(studentId);
     Course course = courseService.findCourseByCode(courseCode);

     if (student == null || course == null) {
         System.out.println("Enrollment failed: Student or Course not found.");
         return;
     }

     // Check for duplicate enrollment
     boolean alreadyEnrolled = dataStore.getEnrollments().stream()
         .anyMatch(e -> e.getStudent().getId() == studentId && e.getCourse().getCode().equalsIgnoreCase(courseCode));
     
     if (alreadyEnrolled) {
         // Throw the custom checked exception
         throw new DuplicateEnrollmentException("Student " + studentId + " is already enrolled in course " + courseCode);
     }
     
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
                return; // Exit after finding and updating
            }
        }
        System.out.println("Failed to assign grade: Enrollment record not found.");
    }
}