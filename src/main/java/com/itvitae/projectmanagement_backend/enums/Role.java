package com.itvitae.projectmanagement_backend.enums;

import java.util.Set;

public enum Role {
    PROJECT_LEADER(Set.of(Permission.CREATE_TASK, Permission.DELETE_TASK, Permission.UPDATE_TASK, Permission.READ_TASK, Permission.CREATE_COMMENT, Permission.DELETE_COMMENT, Permission.UPDATE_COMMENT, Permission.ADD_USER, Permission.REMOVE_USER, Permission.CREATE_PROJECT, Permission.DELETE_PROJECT, Permission.UPDATE_PROJECT, Permission.READ_PROJECT)),
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
