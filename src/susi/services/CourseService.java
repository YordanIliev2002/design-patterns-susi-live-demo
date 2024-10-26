package susi.services;

import susi.entities.Course;
import susi.repositories.course.CourseRepository;

public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherService teacherService;

    public CourseService(CourseRepository courseRepository, TeacherService teacherService) {
        this.courseRepository = courseRepository;
        this.teacherService = teacherService;
    }

    public void addCourse(Course course) {
        if (teacherService.findTeacherById(course.leadingTeacherId()) == null) {
            throw new IllegalArgumentException("Teacher not found");
        }
        courseRepository.save(course);
    }

    public Course findCourseById(String courseId) {
        return courseRepository.findById(courseId);
    }
}
