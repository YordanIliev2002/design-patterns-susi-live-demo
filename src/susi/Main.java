package susi;

import susi.entities.Teacher;
import susi.repositories.teacher.ListTeacherRepository;
import susi.repositories.teacher.TeacherRepository;
import susi.services.TeacherService;

public class Main {
    public static void main(String[] args) {
        TeacherRepository repository = new ListTeacherRepository();

        TeacherService teacherService = new TeacherService(repository);

        teacherService.addTeacher(new Teacher( "123", "John", "Doe"));
    }
}
