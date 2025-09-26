package edu.ccrm.domain;

// Abstract base class for all people in the system
public abstract class Person {
    // Encapsulated fields: private and accessed via getters/setters
    private int id;
    private String fullName;
    private String email;

    public Person(int id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // An abstract method that subclasses MUST implement
    public abstract String getDetails();

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + fullName + ", Email: " + email;
    }
}