package susi.repositories.student;

import susi.entities.Student;

public interface StudentRepository {
    void save(Student student);
    Student findById(String studentId);
}
