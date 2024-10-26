package susi.services;

import susi.entities.Enrollment;
import susi.repositories.enrollment.EnrollmentRepository;

public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final CourseService courseService;
    private final StudentService studentService;

    public EnrollmentService(
        EnrollmentRepository enrollmentRepository,
        CourseService courseService,
        StudentService studentService
    ) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    public void enrollStudent(String studentId, String courseId) {
        validateStudentExists(studentId);
        validateCourseExists(courseId);

        Enrollment enrollment = new Enrollment(studentId, courseId, null, false);
        enrollmentRepository.save(enrollment);
    }

    public void gradeStudent(String studentId, String courseId, int grade) {
        validateStudentExists(studentId);
        validateCourseExists(courseId);

        Enrollment enrollment = enrollmentRepository.find(studentId, courseId);

        if (enrollment.isExpelled()) {
            throw new IllegalArgumentException("Student is expelled from the course");
        }

        Enrollment enrollmentToSave = enrollment.withGrade(grade);
        enrollmentRepository.save(enrollmentToSave);
    }

    public void expelStudent(String studentId, String courseId) {
        validateStudentExists(studentId);
        validateCourseExists(courseId);

        Enrollment enrollment = enrollmentRepository.find(studentId, courseId);

        Enrollment enrollmentToSave = enrollment.withIsExpelled(true);
        enrollmentRepository.save(enrollmentToSave);
    }

    private void validateCourseExists(String courseId) {
        boolean courseExists = courseService.findCourseById(courseId) != null;

        if (!courseExists) {
            throw new IllegalArgumentException("Course not found");
        }
    }

    private void validateStudentExists(String studentId) {
        boolean studentExists = studentService.findStudentById(studentId) != null;

        if (!studentExists) {
            throw new IllegalArgumentException("Student not found");
        }
    }
}
