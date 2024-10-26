package susi.repositories.teacher;

import susi.entities.Teacher;
import java.util.HashMap;

public class HashMapTeacherRepository implements TeacherRepository {
    private final HashMap<String, Teacher> storage = new HashMap<>();

    @Override
    public void save(Teacher teacher) {
        storage.put(teacher.teacherId(), teacher);
    }

    @Override
    public Teacher findById(String id) {
        return storage.get(id);
    }
}
