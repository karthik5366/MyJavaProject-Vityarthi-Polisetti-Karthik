package edu.ccrm.cli;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import edu.ccrm.domain.Grade;
import edu.ccrm.exception.DuplicateEnrollmentException;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.CourseServiceImpl;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.EnrollmentServiceImpl;
import edu.ccrm.service.StudentService;
import edu.ccrm.service.StudentServiceImpl;
import edu.ccrm.util.FileUtils;

public class Main {

    // Instantiate ALL the services we will use
    private static final StudentService studentService = new StudentServiceImpl();
    private static final CourseService courseService = new CourseServiceImpl();
    private static final EnrollmentService enrollmentService = new EnrollmentServiceImpl();
    private static final ImportExportService importExportService = new ImportExportService();
    private static final BackupService backupService = new BackupService(); // NEWLY ADDED
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Campus Course & Records Manager (CCRM)");
        
        while (true) {
            printMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    handleStudentMenu();
                    break;
                case 2:
                    System.out.println("Course Management not yet implemented.");
                    break;
                case 3:
                    // This case is now implemented
                    handleEnrollmentMenu();
                    break;
                case 4:
                    handleImportExportMenu();
                    break;
                case 5:
                    // This case is now implemented
                    handleBackupMenu();
                    break;
                case 0:
                    System.out.println("Thank you for using CCRM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Enrollments & Grades");
        System.out.println("4. Import/Export Data");
        System.out.println("5. Backup & Utilities");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static void handleStudentMenu() {
        // ... (This method remains unchanged from the previous version) ...
        while (true) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add a new Student");
            System.out.println("2. List all Students");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Full Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Registration Number: ");
                    String regNo = scanner.nextLine();
                    edu.ccrm.domain.Student newStudent = new edu.ccrm.domain.Student(id, name, email, regNo);
                    studentService.addStudent(newStudent);
                    System.out.println("Student added successfully!");
                    break;
                case 2:
                    System.out.println("\n--- All Students ---");
                    if (studentService.getAllStudents().isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        for (edu.ccrm.domain.Student student : studentService.getAllStudents()) {
                            System.out.println(student);
                        }
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    
    private static void handleImportExportMenu() {
        // ... (This method also remains unchanged) ...
        System.out.println("\n--- Import/Export Data ---");
        String studentsFilePath = "data/students.csv";
        System.out.println("Using default file path: " + studentsFilePath);
        System.out.println("\n1. Import Students from CSV");
        System.out.println("2. Export Students to CSV");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                importExportService.importStudents(studentsFilePath);
                break;
            case 2:
                importExportService.exportStudents(studentsFilePath);
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // ==========================================================
    // NEW METHOD FOR PHASE 5: HANDLING ENROLLMENTS AND GRADES
    // ==========================================================
    private static void handleEnrollmentMenu() {
        System.out.println("\n--- Manage Enrollments & Grades ---");
        System.out.println("1. Enroll Student in a Course");
        System.out.println("2. Assign Grade to Student");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Enter Student ID: ");
            int studentId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Course Code (e.g., CS101): ");
            String courseCode = scanner.nextLine();

            // Here we use a try-catch block to handle our custom exception
            try {
                enrollmentService.enrollStudent(studentId, courseCode);
            } catch (DuplicateEnrollmentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } else if (choice == 2) {
            System.out.print("Enter Student ID: ");
            int studentId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Course Code: ");
            String courseCode = scanner.nextLine();
            System.out.print("Enter Grade (S, A, B, C, D, F): ");
            String gradeInput = scanner.nextLine().toUpperCase();
            
            try {
                // Convert the user's string input to a Grade enum constant
                Grade grade = Grade.valueOf(gradeInput);
                enrollmentService.assignGrade(studentId, courseCode, grade);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid grade entered. Please use S, A, B, C, D, or F.");
            }
        }
    }
    
    // ==========================================================
    // NEW METHOD FOR PHASE 5: HANDLING BACKUP AND UTILITIES
    // ==========================================================
    private static void handleBackupMenu() {
        System.out.println("\n--- Backup & Utilities ---");
        String studentFile = "data/students.csv"; // Example source file
        String backupDir = "backups";             // Example destination directory

        System.out.println("1. Create a backup of student data");
        System.out.println("2. Calculate total size of backups directory (Recursive)");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch(choice) {
            case 1:
                System.out.println("Backing up '" + studentFile + "' to '" + backupDir + "' directory...");
                backupService.backupData(studentFile, backupDir);
                break;
            case 2:
                // Here we call the recursive utility method
                try {
                    long size = FileUtils.calculateDirectorySize(Paths.get(backupDir));
                    System.out.printf("Total size of '%s' directory: %,d bytes%n", backupDir, size);
                } catch (IOException e) {
                    System.err.println("Could not calculate directory size: " + e.getMessage());
                }
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }
}