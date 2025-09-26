package edu.ccrm.domain;

public class Enrollment {
    private Student student;
    private Course course;
    private Grade grade; // Can be null if not yet graded

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    // Getters
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public Grade getGrade() { return grade; }

    // Setter only for grade, as student/course shouldn't change
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Enrollment [Student=" + student.getFullName() + ", Course=" + course.getTitle() + ", Grade=" + grade + "]";
    }
}