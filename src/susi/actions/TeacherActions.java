package susi.actions;

import susi.services.EnrollmentService;

public class TeacherActions {
    private final EnrollmentService enrollmentService;

    public TeacherActions(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    public void enrollStudent(String studentId, String courseId) {
        enrollmentService.enrollStudent(studentId, courseId);
    }

    public void expelStudent(String studentId, String courseId) {
        enrollmentService.expelStudent(studentId, courseId);
    }

    public void gradeStudent(String studentId, String courseId, int grade) {
        enrollmentService.gradeStudent(studentId, courseId, grade);
    }
}
