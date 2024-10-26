package susi.actions;

import susi.enums.Role;
import susi.services.AuthService;

public class ActionsDispatcher {
    private final AdminActions adminActions;
    private final TeacherActions teacherActions;
    private final AuthService authService;

    public ActionsDispatcher(AdminActions adminActions, TeacherActions teacherActions, AuthService authService) {
        this.adminActions = adminActions;
        this.teacherActions = teacherActions;
        this.authService = authService;
    }

    public AdminActions loginAsAdmin(String username, String password) {
        checkCredentials(username, password);
        checkUserHasRole(username, Role.ADMIN);
        return adminActions;
    }

    public TeacherActions loginAsTeacher(String username, String password) {
        checkCredentials(username, password);
        checkUserHasRole(username, Role.TEACHER);
        return teacherActions;
    }

    private void checkUserHasRole(String username, Role role) {
        if (!authService.isUserAuthorized(username, role)) {
            throw new IllegalArgumentException("Insufficient role");
        }
    }

    private void checkCredentials(String username, String password) {
        if (!authService.checkCredentials(username, password)) {
            throw new IllegalArgumentException("Invalid credentials");
        }
    }
}
