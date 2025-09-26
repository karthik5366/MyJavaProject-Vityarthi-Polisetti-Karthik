# Campus Course & Records Manager (CCRM)

### A "Programming in Java" Project Submission

**Author:** Polisetti Hari Sri Raj Karthik
**Registration No:** 23BCY10308
**University:** VIT Bhopal University

---

## Project Overview

The Campus Course & Records Manager (CCRM) is a console-based Java application designed to serve as a lightweight administrative tool for an educational institution. It provides core functionalities for managing students, courses, and enrollments through an interactive command-line interface.

This project was built from the ground up to demonstrate a comprehensive understanding of the Java SE platform, from fundamental language constructs to advanced concepts like Object-Oriented Programming, Design Patterns, and modern File I/O. One of the most interesting challenges was structuring the project with a service layer using interfaces, which helped create a clean separation between the application's logic and its data models.

## How to Use This Project (USAGE)

The application is straightforward to run either from a standard command line or directly within the Eclipse IDE.

### Required Data Files
Before running, please ensure a `data` folder exists in the project's root directory. This folder should contain a `students.csv` file for the import/export functionality to work correctly. A sample `students.csv` is included in the repository.

### Option 1: Running from the Command Line
1.  Open a terminal and navigate to the project's root directory.
2.  **Compile:** Compile all source files into the `bin` directory.
    ```sh
    javac -d bin src/edu/ccrm/*/*.java src/edu/ccrm/*/*/*.java
    ```
3.  **Run:** Execute the main class to start the application.
    ```sh
    java -cp bin edu.ccrm.cli.Main
    ```

### Option 2: Running from Eclipse IDE
1.  Import the project as an "Existing Projects into Workspace".
2.  Locate the `Main.java` file within the `src/edu/ccrm/cli` package.
3.  Right-click on `Main.java` and select **Run As > Java Application**.
4.  The application will start running in the Eclipse "Console" view.

---

## Technical Documentation

### Java Version Used
This project was developed and tested on **Java SE 21.0.7 LTS**.

### Core Java Concepts Explained

#### Java Architecture: JDK vs JRE vs JVM
*   **JVM (Java Virtual Machine):** The core component that actually runs Java bytecode. It provides the platform-independent runtime environment.
*   **JRE (Java Runtime Environment):** A package that includes the JVM plus the standard Java libraries. It contains everything needed to *run* a compiled Java application.
*   **JDK (Java Development Kit):** The complete development package. It contains everything in the JRE, plus the tools needed to *write and compile* Java code, such as the `javac` compiler.

#### Java Editions: SE vs EE vs ME
*   **Java SE (Standard Edition):** The foundational platform for Java. It provides the core APIs and is used for developing desktop and server applications. This project is built using Java SE.
*   **Java EE (Enterprise Edition):** Built on top of Java SE, it provides a larger set of APIs for building large-scale, multi-tiered, and distributed enterprise applications (e.g., web services).
*   **Java ME (Micro Edition):** A lightweight subset of Java SE, designed for resource-constrained devices like embedded systems and older mobile phones.

---

## Setup & Execution Proof (Screenshots)

All required screenshots demonstrating the project setup and execution are located in the `/screenshots` directory of this repository. This includes:
*   `jdk_version.png`: Verification of the Java 21 LTS installation.
*   `eclipse_project_setup.png`: A view of the project creation dialog in Eclipse.
*   `eclipse_program_running.png`: The application running in the Eclipse console.
*   `folder_structure.png`: The final folder structure showing backup and export files.

---

## Syllabus Topic to Code Mapping Table

This table demonstrates where each mandatory technical requirement from the project syllabus is implemented in the source code.

| Syllabus Topic | File / Class / Method Location |
| --- | --- |
| **Project Structure & Packages** | The entire project is organized into packages like `edu.ccrm.domain`, `edu.ccrm.service`, etc. |
| **Loops & Control Flow** | `edu/ccrm/cli/Main.java` (main `while` loop, `switch` for menus, `for` loop for listing students) |
| **OOP: Encapsulation** | `edu/ccrm/domain/Person.java` (private fields with public getters/setters) |
| **OOP: Inheritance** | `edu/ccrm/domain/Student.java` (uses the `extends Person` keyword) |
| **OOP: Abstraction** | `edu/ccrm/domain/Person.java` (declared as `public abstract class`) |
| **OOP: Polymorphism** | `Student.java` and `Instructor.java` override the `toString()` method from a common superclass. |
| **Design Pattern: Singleton** | `edu/ccrm/service/DataStore.java` (implements a private constructor and a public static `getInstance()` method) |
| **Design Pattern: Builder** | `edu/ccrm/domain/Course.java` (contains the nested static `Builder` class for object construction) |
| **Exception Handling: Custom** | `edu/ccrm/exception/DuplicateEnrollmentException.java` is defined as a checked exception. |
| **Exception Handling: try-catch** | `edu/ccrm/cli/Main.java::handleEnrollmentMenu()` (catches `DuplicateEnrollmentException` to prevent crashes) |
| **File I/O: NIO.2 & Streams** | `edu/ccrm/io/ImportExportService.java` (Uses `java.nio.file.Files` and `java.nio.file.Path` for all file operations) |
| **Recursion** | `edu/ccrm/util/FileUtils.java::calculateDirectorySize()` (a method that calls itself to process subdirectories) |
| **Streams API** | `edu/ccrm/io/ImportExportService.java::importStudents()` (uses `.stream().skip().map().forEach()` for processing CSV data) |
| **Enums with Constructors** | `edu/ccrm/domain/Grade.java` (enum constants hold an integer `gradePoint` value) |
| **Interfaces** | `edu/ccrm/service/StudentService.java` (defines the contract for `StudentServiceImpl.java`) |
| **Date/Time API** | `edu/ccrm/domain/Student.java` (uses `java.time.LocalDate` for the `enrollmentDate` field) |

---

## Notes on Assertions

Assertions are a development tool used to check for internal logical errors by verifying assumptions made by the programmer. They are disabled by default.

**How to Enable Assertions:**
To run the application with assertions enabled from the command line, use the `-ea` flag:
```sh
java -ea -cp bin edu.ccrm.cli.Main