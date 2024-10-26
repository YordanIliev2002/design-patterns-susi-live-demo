package susi;

import susi.actions.ActionsDispatcher;
import susi.actions.AdminActions;
import susi.actions.TeacherActions;
import susi.entities.Course;
import susi.entities.Student;
import susi.entities.Teacher;
import susi.enums.Role;
import susi.repositories.course.LinkedListCourseRepository;
import susi.repositories.enrollment.ListEnrollmentRepository;
import susi.repositories.role.ListRoleAssignmentRepository;
import susi.repositories.student.SetStudentRepository;
import susi.repositories.teacher.ListTeacherRepository;
import susi.repositories.user.HashMapUserRepository;
import susi.services.AuthService;
import susi.services.CourseService;
import susi.services.EnrollmentService;
import susi.services.StudentService;
import susi.services.TeacherService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ActionsDispatcher susi = createSusi();
        // Admin
        AdminActions asAdmin = susi.loginAsAdmin("admin", "super-secret-password");
        asAdmin.addStudent(new Student("student-1", "Yordan", "Iliev", 22));
        asAdmin.addUser("yordan-iliev", "1234", List.of(Role.STUDENT));

        asAdmin.addTeacher(new Teacher("teacher-1", "Phillip", "Yankov"));
        asAdmin.addUser("phillip-yankov", "4321", List.of(Role.TEACHER));

        asAdmin.addCourse(new Course("course-1", "Design Patterns", "teacher-1"));

        // Teacher
        TeacherActions asTeacher = susi.loginAsTeacher("phillip-yankov", "4321");
        asTeacher.enrollStudent("student-1", "course-1");
        asTeacher.gradeStudent( "student-1", "course-1", 6);
    }

    private static ActionsDispatcher createSusi() {
        // Reminder that we can swap out the repositories here, without modifying the services
        TeacherService teacherService = new TeacherService(new ListTeacherRepository());
        StudentService studentService = new StudentService(new SetStudentRepository());
        CourseService courseService = new CourseService(new LinkedListCourseRepository(), teacherService);
        EnrollmentService enrollmentService = new EnrollmentService(new ListEnrollmentRepository(), courseService, studentService);
        AuthService authService = new AuthService(new HashMapUserRepository(), new ListRoleAssignmentRepository());
        authService.createUser("admin", "super-secret-password", List.of(Role.ADMIN));

        AdminActions adminActions = new AdminActions(authService, studentService, teacherService, courseService, enrollmentService);
        TeacherActions teacherActions = new TeacherActions(enrollmentService);

        return new ActionsDispatcher(adminActions, teacherActions, authService);
    }
}
