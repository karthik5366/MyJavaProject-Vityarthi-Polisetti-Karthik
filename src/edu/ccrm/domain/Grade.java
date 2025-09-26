package edu.ccrm.domain;

public enum Grade {
    // Enum constants with associated grade points
    S(10),
    A(9),
    B(8),
    C(7),
    D(6),
    F(0);

    // Field to store the grade point
    private final int gradePoint;

    // Constructor for the enum
    private Grade(int gradePoint) {
        this.gradePoint = gradePoint;
    }

    // Getter for the grade point
    public int getGradePoint() {
        return gradePoint;
    }
}