package edu.ccrm.service;

import java.util.ArrayList;
import java.util.List;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;

// This class uses the Singleton design pattern.
public class DataStore {

    // 1. The single, static instance of the class.
    private static DataStore instance;

    // In-memory storage for our domain objects.
    private final List<Student> students;
    private final List<Course> courses;
    private final List<Enrollment> enrollments;

    // 2. A private constructor to prevent anyone else from creating an instance.
    private DataStore() {
        // Initialize the lists when the instance is created.
        students = new ArrayList<>();
        courses = new ArrayList<>();
        enrollments = new ArrayList<>();
    }

    // 3. The public, static method to get the single instance of the class.
    public static synchronized DataStore getInstance() {
        // "Lazy initialization": create the instance only if it doesn't exist yet.
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    // Public getters to allow other parts of the app to access the data lists.
    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }
}