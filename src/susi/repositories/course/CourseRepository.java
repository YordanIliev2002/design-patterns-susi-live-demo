package susi.repositories.course;

import susi.entities.Course;

public interface CourseRepository {
    void save(Course course);
    Course findById(String courseId);
}
