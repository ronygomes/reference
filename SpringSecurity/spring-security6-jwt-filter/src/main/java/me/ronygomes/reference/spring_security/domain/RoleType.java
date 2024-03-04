package me.ronygomes.reference.spring_security.domain;

public enum RoleType {
    ADMIN, USER;

    public static RoleType findRoleTypeByName(String roleName) {
        for (RoleType role : values()) {
            if (role.name().equalsIgnoreCase(roleName)) {
                return role;
            }
        }
        return null;
    }

    public static boolean isValidRoleType(String roleName) {
        return findRoleTypeByName(roleName) != null;
    }
}
