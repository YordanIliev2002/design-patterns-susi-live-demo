package susi.repositories.teacher;

import susi.entities.Teacher;

import java.util.ArrayList;
import java.util.List;

public class ListTeacherRepository implements TeacherRepository {
    private final List<Teacher> storage = new ArrayList<>();

    @Override
    public void save(Teacher teacher) {
        removeIfExists(teacher.teacherId());
        storage.add(teacher);
    }

    @Override
    public Teacher findById(String id) {
        for (Teacher teacher : storage) {
            if (teacher.teacherId().equals(id)) {
                return teacher;
            }
        }
        return null;
    }

    private void removeIfExists(String teacherId) {
        storage.removeIf(teacher -> teacher.teacherId().equals(teacherId));
    }
}
