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

    public Enrollment withIsExpelled(boolean isExpelled) {
        return new Enrollment(studentId, courseId, grade, isExpelled);
    }
}
