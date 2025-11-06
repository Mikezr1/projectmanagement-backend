package com.itvitae.projectmanagement_backend.enums;

import java.util.Set;

public enum Role {
    PROJECT_LEADER(Set.of(Permission.CREATE_TASK, Permission.DELETE_TASK)),
    DEVELOPER(Set.of(Permission.READ_COMMENT, Permission.CREATE_COMMENT)),
    CUSTOMER(Set.of());

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
