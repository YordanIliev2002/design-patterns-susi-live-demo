package susi.services;

import susi.entities.Teacher;
import susi.repositories.teacher.TeacherRepository;

public class TeacherService {
    private final TeacherRepository repository;

    public TeacherService(TeacherRepository repository) {
        this.repository = repository;
    }

    public void addTeacher(Teacher teacher) {
        repository.save(teacher);
    }

    public Teacher findTeacherById(String id) {
        return repository.findById(id);
    }
}
