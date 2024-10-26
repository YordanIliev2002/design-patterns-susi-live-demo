package susi.repositories.teacher;

import susi.entities.Teacher;

public interface TeacherRepository {
    void save(Teacher teacher);
    Teacher findById(String id);
}
