# Campus Course & Records Manager (CCRM)

A comprehensive, console-based Java application for managing student and course records, built as a practical demonstration of modern Java SE principles.

**Author:** Polisetti Hari Sri Raj Karthik  
**Registration No:** 23BCY10308  
**University:** VIT Bhopal University

---

## ► Overview

The Campus Course & Records Manager (CCRM) is a lightweight administrative tool designed to handle the core academic operations of an educational institution. It provides a robust, interactive command-line interface (CLI) for managing students, courses, enrollments, and academic records.

This project was developed to apply and demonstrate a deep understanding of the Java SE platform, spanning from fundamental language constructs to advanced architectural concepts. A key focus was placed on creating a clean, maintainable codebase by separating concerns into distinct layers (domain, service, IO, CLI). One of the most insightful challenges was designing the service layer with interfaces, which established a clear contract for the application's business logic and greatly improved modularity.

### Key Features
*   **Student Management:** Full CRUD (Create, Read, Update, Delete) operations for student records.
*   **Course Management:** Functionality to define courses, assign instructors, and manage course details.
*   **Enrollment System:** A complete workflow to enroll and unenroll students from courses and assign grades.
*   **Data Persistence:** Robust import/export functionality using CSV files, powered by Java's modern NIO.2 library.
*   **Automated Backups:** A utility to create timestamped backups of application data to prevent data loss.
*   **Recursive File Utilities:** A practical demonstration of recursion to perform operations on the file system, such as calculating directory sizes.

---

## ► Getting Started

### Prerequisites
*   **Java Development Kit (JDK):** This project was built and tested on **Java SE 21.0.7 LTS**. Any modern LTS version (17+) should be compatible.
*   **Data Directory:** The application requires a `data` folder in the project root containing a `students.csv` file for the import/export features. A sample is provided in the repository.

### Running the Application

#### Option 1: From the Command Line
1.  Navigate to the project's root directory in your terminal.
2.  **Compile:**
    ```sh
    javac -d bin src/edu/ccrm/*/*.java src/edu/ccrm/*/*/*.java
    ```
3.  **Run:**
    ```sh
    java -cp bin edu.ccrm.cli.Main
    ```

#### Option 2: From the Eclipse IDE
1.  Import the project using **File > Import > General > Existing Projects into Workspace**.
2.  Locate `Main.java` in the `src/edu/ccrm/cli` package.
3.  Right-click the file and select **Run As > Java Application**. The program will launch in the Eclipse Console view.

---

## ► Architectural Highlights & Concept Demonstration

This project was built to showcase proficiency in several key areas of Java development. Below is a breakdown of key concepts and where they are applied in the code.

### Object-Oriented Programming
The entire application is founded on OOP principles to create a logical and maintainable structure.
*   **Inheritance:** Implemented in `edu.ccrm.domain.Student`, which `extends` the base `Person` class to share common attributes and behavior.
*   **Abstraction:** The `Person` class is declared as `abstract`, preventing direct instantiation and defining a contract for subclasses.
*   **Encapsulation:** All domain models (e.g., `Person`, `Student`) use `private` fields with `public` getters and setters to protect their internal state.
*   **Polymorphism:** Subclasses like `Student` and `Instructor` provide their own implementations by overriding the `toString()` method.

### Design Patterns
*   **Singleton Pattern:** The `edu.ccrm.service.DataStore` class is implemented as a Singleton to ensure a single, globally accessible in-memory "database" for the application's runtime data.
*   **Builder Pattern:** The `edu.ccrm.domain.Course` class features a nested static `Builder` to allow for the clean, step-by-step construction of complex and immutable `Course` objects.

### Core Java Features & APIs
*   **Interfaces & Implementations:** The `edu.ccrm.service` package defines contracts (e.g., `StudentService.java`) which are fulfilled by concrete implementation classes (`StudentServiceImpl.java`), promoting a loosely coupled architecture.
*   **Custom Exception Handling:** `edu.ccrm.exception.DuplicateEnrollmentException` is a custom checked exception created to enforce a specific business rule. It is handled gracefully with a `try-catch` block in the CLI.
*   **Modern File I/O (NIO.2):** All file operations in `edu.ccrm.io` use the modern `java.nio.file` API (`Path`, `Files`, `Paths`) for improved performance and more robust error handling compared to legacy I/O.
*   **Java Streams API:** The Streams API is used for efficient, functional-style data processing, most notably in the `ImportExportService` for parsing CSV data in a declarative pipeline.
*   **Recursion:** A classic recursive algorithm is implemented in `edu.ccrm.util.FileUtils` with the `calculateDirectorySize()` method, which traverses a directory tree to compute its total size.
*   **Date/Time API:** The modern `java.time.LocalDate` from Java 8 is used in the `Student` class to handle date-related information in a thread-safe and immutable way.
*   **Enums with State:** The `edu.ccrm.domain.Grade` enum is an example of an advanced enum where each constant holds an associated state (an integer `gradePoint`), demonstrating that enums can be more than simple constants.

---

## ► Appendix

### Setup & Execution Proof (Screenshots)
All required screenshots verifying the development environment and application execution are available in the `/screenshots` directory of this repository.

### Notes on Assertions
Assertions are used for internal consistency checks during development. They are disabled by default but can be enabled at runtime by passing the `-ea` flag to the JVM.
```sh
java -ea -cp bin edu.ccrm.cli.Main

