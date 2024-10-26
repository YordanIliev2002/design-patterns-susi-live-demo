package susi.entities;

import susi.enums.Role;

import java.time.Instant;

public record RoleAssignment(String username, Role role, Instant grantedOn) {}
