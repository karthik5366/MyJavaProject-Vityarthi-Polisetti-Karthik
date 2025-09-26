package edu.ccrm.service;

import java.util.List;
import edu.ccrm.domain.Course;

public interface CourseService {
    void addCourse(Course course);
    Course findCourseByCode(String code);
    List<Course> getAllCourses();
}