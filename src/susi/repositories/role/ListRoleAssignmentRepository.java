package susi.repositories.role;

import susi.entities.RoleAssignment;

import java.util.ArrayList;
import java.util.List;

public class ListRoleAssignmentRepository implements RoleAssignmentRepository {
    private final List<RoleAssignment> assignments = new ArrayList<>();

    @Override
    public void save(RoleAssignment assignment) {
        assignments.add(assignment);
    }

    @Override
    public List<RoleAssignment> findByUsername(String username) {
        List<RoleAssignment> result = new ArrayList<>();

        for (RoleAssignment assignment : assignments) {
            if (assignment.username().equals(username)) {
                result.add(assignment);
            }
        }
        return result;
    }
}
