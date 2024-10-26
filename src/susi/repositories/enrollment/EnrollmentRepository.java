package susi.repositories.enrollment;

import susi.entities.Enrollment;

public interface EnrollmentRepository {
    void save(Enrollment enrollment);
    Enrollment find(String studentId, String courseId);
}
