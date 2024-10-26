package susi.services;

import susi.entities.Student;
import susi.repositories.student.StudentRepository;

public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public void addStudent(Student student) {
        repository.save(student);
    }

    public Student findStudentById(String studentId) {
        return repository.findById(studentId);
    }
}
