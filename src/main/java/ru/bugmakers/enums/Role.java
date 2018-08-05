package ru.bugmakers.enums;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ivan
 */
public enum Role {

    ADMIN,
    OPERATOR,
    ARTIST,
    LISTENER;

    private static final Set<String> roles;

    static {
        roles = new HashSet<>(Role.values().length);
        for (Role r : Role.values()) {
            roles.add(r.name());
        }
    }

    public static Set<String> getRoles() {
        return roles;
    }

}
