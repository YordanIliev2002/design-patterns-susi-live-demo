package susi.repositories.enrollment;

import susi.entities.Enrollment;

import java.util.ArrayList;
import java.util.List;

public class ListEnrollmentRepository implements EnrollmentRepository {
    private final List<Enrollment> enrollmentList = new ArrayList<>();

    public void save(Enrollment enrollment) {
        removeEnrollment(enrollment.studentId(), enrollment.courseId());
        enrollmentList.add(enrollment);
    }

    private void removeEnrollment(String studentId, String courseId) {
        enrollmentList.removeIf(enrollment ->
            enrollment.studentId().equals(studentId)
                && enrollment.courseId().equals(courseId));
    }

    @Override
    public Enrollment find(String studentId, String courseId) {
        for (Enrollment enrollment : enrollmentList) {
            if (enrollment.studentId().equals(studentId) && enrollment.courseId().equals(courseId)) {
                return enrollment;
            }
        }
        return null;
    }
}
