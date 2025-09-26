package edu.ccrm.service;

import java.util.List;
import edu.ccrm.domain.Course;

public class CourseServiceImpl implements CourseService {
    private final DataStore dataStore = DataStore.getInstance();

    @Override
    public void addCourse(Course course) {
        dataStore.getCourses().add(course);
    }

    @Override
    public Course findCourseByCode(String code) {
        return dataStore.getCourses().stream()
            .filter(course -> course.getCode().equalsIgnoreCase(code))
            .findFirst()
            .orElse(null);
    }

    @Override
    public List<Course> getAllCourses() {
        return dataStore.getCourses();
    }
}