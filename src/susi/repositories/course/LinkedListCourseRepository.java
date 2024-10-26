package susi.repositories.course;

import susi.entities.Course;

import java.util.LinkedList;
import java.util.List;

public class LinkedListCourseRepository implements CourseRepository {
    private final List<Course> courses = new LinkedList<>();

    @Override
    public void save(Course course) {
        courses.add(course);
    }

    @Override
    public Course findById(String courseId) {
        for (Course course : courses) {
            if (course.courseId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }
}
