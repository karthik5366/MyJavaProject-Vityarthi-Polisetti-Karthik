package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import edu.ccrm.domain.Student;
import edu.ccrm.service.StudentService;
import edu.ccrm.service.StudentServiceImpl;

public class ImportExportService {
    private final StudentService studentService = new StudentServiceImpl();
    
    // --- STUDENT IMPORT/EXPORT ---

    public void importStudents(String filePath) {
        Path path = Paths.get(filePath);
        try (Stream<String> lines = Files.lines(path)) {
            lines.skip(1) // Skip the header row
                 .map(line -> line.split(","))
                 .map(data -> new Student(Integer.parseInt(data[0]), data[1], data[2], data[3]))
                 .forEach(studentService::addStudent);
            System.out.println("Students imported successfully from " + filePath);
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }

    public void exportStudents(String filePath) {
        List<Student> students = studentService.getAllStudents();
        String header = "ID,FullName,Email,RegNo";
        
        // Convert each student object to a CSV string line
        List<String> lines = students.stream()
            .map(s -> String.format("%d,%s,%s,%s", s.getId(), s.getFullName(), s.getEmail(), s.getRegNo()))
            .collect(Collectors.toList());
        
        lines.add(0, header); // Add the header at the beginning

        try {
            Files.write(Paths.get(filePath), lines);
            System.out.println("Students exported successfully to " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    // TODO: Add similar methods for Courses
}