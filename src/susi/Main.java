package susi;

import susi.entities.Course;
import susi.entities.Student;
import susi.entities.Teacher;
import susi.repositories.course.LinkedListCourseRepository;
import susi.repositories.enrollment.ListEnrollmentRepository;
import susi.repositories.student.SetStudentRepository;
import susi.repositories.teacher.ListTeacherRepository;
import susi.services.CourseService;
import susi.services.EnrollmentService;
import susi.services.StudentService;
import susi.services.TeacherService;

public class Main {
    public static void main(String[] args) {
        // Reminder that we can swap out the repositories here, without modifying the services
        TeacherService teacherService = new TeacherService(new ListTeacherRepository());
        StudentService studentService = new StudentService(new SetStudentRepository());
        CourseService courseService = new CourseService(new LinkedListCourseRepository(), teacherService);
        EnrollmentService enrollmentService = new EnrollmentService(
            new ListEnrollmentRepository(),
            courseService,
            studentService
        );

        teacherService.addTeacher(new Teacher( "teacher-1", "Teacher", "Teacherson"));
        studentService.addStudent(new Student("student-1", "Student", "Studentson", 20));

        courseService.addCourse(new Course("course-1", "Design Patterns", "teacher-1"));
        enrollmentService.enrollStudent( "student-1", "course-1");
        enrollmentService.gradeStudent( "student-1", "course-1", 5);
    }
}
