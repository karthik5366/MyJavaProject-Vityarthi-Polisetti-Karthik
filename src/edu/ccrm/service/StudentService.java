package edu.ccrm.service;

import java.util.List;
import edu.ccrm.domain.Student;

public interface StudentService {
    void addStudent(Student student);
    Student findStudentById(int id);
    List<Student> getAllStudents();
}