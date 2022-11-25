package com.store.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum Role {


    USER,
    ADMIN;

    public List<Role> getPermissions() {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.USER);
        roles.add(Role.ADMIN);
        return roles;
    }


    public List<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                        .collect(Collectors.toList());
    }










//    USER(Set.of(Permission.USER_READ)),
//    ADMIN(Set.of(Permission.USER_READ, Permission.USER_WRITE ));
//    private final Set<Permission> permissions;
//
//    Role(Set<Permission> permissions) {
//        this.permissions = permissions;
//    }
//
//    public Set<Permission> getPermissions() {
//        return permissions;
//    }
//    public Set<SimpleGrantedAuthority> getAuthorities() {
//        return getPermissions().stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                        .collect(Collectors.toSet());
//    }
}
