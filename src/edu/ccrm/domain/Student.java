package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// The "extends Person" keyword establishes the inheritance relationship.
public class Student extends Person {

    // Fields that are specific to a Student
    private String regNo;
    private LocalDate enrollmentDate;
    private List<Course> enrolledCourses; // This will show an error until Course.java is created. That's OK.

    // This is the constructor. It's required.
    public Student(int id, String fullName, String email, String regNo) {
        // The 'super()' call MUST be the first line in the constructor.
        // It calls the constructor of the parent class (Person).
        super(id, fullName, email);

        // Initialize the Student-specific fields
        this.regNo = regNo;
        this.enrollmentDate = LocalDate.now();
        this.enrolledCourses = new ArrayList<>();
    }

    // --- Getters and Setters ---
    // This is the correct syntax for a getter method.
    public String getRegNo() {
        return regNo;
    }

    // This is the correct syntax for a setter method.
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    // A method to add a course to the student's list
    public void enrollInCourse(Course course) {
        this.enrolledCourses.add(course);
    }
    
    // This method is required because it's abstract in the Person class.
    @Override
    public String getDetails() {
        return "Student - RegNo: " + regNo + ", Name: " + getFullName();
    }

    @Override
    public String toString() {
        return getDetails();
    }
}