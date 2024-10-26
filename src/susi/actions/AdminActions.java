package susi.actions;

import susi.entities.Course;
import susi.entities.Student;
import susi.entities.Teacher;
import susi.enums.Role;
import susi.services.AuthService;
import susi.services.CourseService;
import susi.services.EnrollmentService;
import susi.services.StudentService;
import susi.services.TeacherService;

import java.util.List;

public class AdminActions {
    private final AuthService authService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    public AdminActions(
        AuthService authService,
        StudentService studentService,
        TeacherService teacherService,
        CourseService courseService,
        EnrollmentService enrollmentService
    ) {
        this.authService = authService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
    }

    public void addUser(String username, String password, List<Role> roles) {
        authService.createUser(username, password, roles);
    }

    public void addStudent(Student student) {
        studentService.addStudent(student);
    }

    public void addTeacher(Teacher studentId) {
        teacherService.addTeacher(studentId);
    }

    public void addCourse(Course course) {
        courseService.addCourse(course);
    }

    public void enrollStudent(String studentId, String courseId) {
        enrollmentService.enrollStudent(studentId, courseId);
    }

    public void expelStudent(String studentId, String courseId) {
        enrollmentService.expelStudent(studentId, courseId);
    }
}
