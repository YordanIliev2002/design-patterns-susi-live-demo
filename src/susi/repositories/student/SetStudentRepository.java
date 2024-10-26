package susi.repositories.student;

import susi.entities.Student;

import java.util.HashSet;
import java.util.Set;

public class SetStudentRepository implements StudentRepository {
    private final Set<Student> students = new HashSet<>();

    @Override
    public void save(Student student) {
        students.add(student);
    }

    @Override
    public Student findById(String studentId) {
        for (Student student : students) {
            if (student.studentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }
}
