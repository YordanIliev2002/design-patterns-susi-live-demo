package susi.services;

import susi.entities.RoleAssignment;
import susi.entities.User;
import susi.enums.Role;
import susi.repositories.role.RoleAssignmentRepository;
import susi.repositories.user.UserRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private final UserRepository userRepository;
    private final RoleAssignmentRepository roleAssignmentRepository;

    public AuthService(UserRepository userRepository, RoleAssignmentRepository roleAssignmentRepository) {
        this.userRepository = userRepository;
        this.roleAssignmentRepository = roleAssignmentRepository;
    }

    public void createUser(String username, String password, List<Role> roles) {
        if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("User already exists");
        }

        User user = new User(username, password);
        userRepository.save(user);

        for (Role role : roles) {
            RoleAssignment roleAssignment = new RoleAssignment(username, role, Instant.now());
            roleAssignmentRepository.save(roleAssignment);
        }
    }

    public boolean checkCredentials(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.password().equals(password);
    }

    public boolean isUserAuthorized(String username, Role requiredRole) {
        List<Role> userRoles = getRolesOfUser(username);
        return userRoles.contains(requiredRole);
    }

    public List<Role> getRolesOfUser(String username) {
        List<RoleAssignment> roleAssignments = roleAssignmentRepository.findByUsername(username);

        List<Role> roles = new ArrayList<>();
        for (RoleAssignment roleAssignment : roleAssignments) {
            roles.add(roleAssignment.role());
        }
        return roles;
    }
}
