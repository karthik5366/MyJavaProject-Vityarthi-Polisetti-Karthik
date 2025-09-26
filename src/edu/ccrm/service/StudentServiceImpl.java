package edu.ccrm.service;

import java.util.List;
import edu.ccrm.domain.Student;

public class StudentServiceImpl implements StudentService {
    // Get a reference to our single source of data
    private final DataStore dataStore = DataStore.getInstance();

    @Override
    public void addStudent(Student student) {
        dataStore.getStudents().add(student);
    }

    @Override
    public Student findStudentById(int id) {
        // Using a modern Java Stream to find the student
        return dataStore.getStudents().stream()
            .filter(student -> student.getId() == id)
            .findFirst()
            .orElse(null); // Return null if not found
    }

    @Override
    public List<Student> getAllStudents() {
        return dataStore.getStudents();
    }
}