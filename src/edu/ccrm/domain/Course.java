package edu.ccrm.domain;

public class Course {
    // Fields for the Course
    private final String code; // e.g., "CS101"
    private final String title; // e.g., "Introduction to Programming"
    private final int credits;
    private final Instructor instructor;
    private final Semester semester;
    private final String department;

    // PRIVATE constructor that takes a Builder object
    private Course(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
        this.instructor = builder.instructor;
        this.semester = builder.semester;
        this.department = builder.department;
    }

    // Public getters for all fields
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public Instructor getInstructor() { return instructor; }
    public Semester getSemester() { return semester; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return "Course [code=" + code + ", title=" + title + ", credits=" + credits + "]";
    }

    // The public static nested Builder class
    public static class Builder {
        // Builder has the same fields as the outer class
        private String code;
        private String title;
        private int credits;
        private Instructor instructor;
        private Semester semester;
        private String department;

        // "with" methods to set properties, returning the builder for chaining
        public Builder withCode(String code) {
            this.code = code;
            return this; // Return the builder itself
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withCredits(int credits) {
            this.credits = credits;
            return this;
        }

        public Builder withInstructor(Instructor instructor) {
            this.instructor = instructor;
            return this;
        }

        public Builder withSemester(Semester semester) {
            this.semester = semester;
            return this;
        }

        public Builder withDepartment(String department) {
            this.department = department;
            return this;
        }

        // The final build() method to create the Course object
        public Course build() {
            // Here you can add validation if needed
            return new Course(this);
        }
    }
}