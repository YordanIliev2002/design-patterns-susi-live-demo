package susi.repositories.role;

import susi.entities.RoleAssignment;

import java.util.List;

public interface RoleAssignmentRepository {
    void save(RoleAssignment assignment);
    List<RoleAssignment> findByUsername(String username);
}
