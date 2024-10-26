package susi.entities;

public record Enrollment (
    String studentId,
    String courseId,
    Integer grade,
    boolean isExpelled
) {
    public Enrollment withGrade(int grade) {
        return new Enrollment(studentId, courseId, grade, isExpelled);
    }
}
