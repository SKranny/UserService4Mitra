package com.example.UserService.mappers;

import com.example.UserService.constants.RoleType;
import com.example.UserService.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleSetMapper {
    private final RoleMapper roleMapper;

    public Set<Role> toRoleSet(Set<RoleType> roleTypes) {
        if (CollectionUtils.isEmpty(roleTypes)) {
            roleTypes = Collections.singleton(RoleType.ROLE_USER);
        }

        return roleTypes.stream()
                .map(roleMapper::toRole)
                .collect(Collectors.toSet());
    }

    public Set<RoleType> toRoleTypeSet(Set<Role> roles) {
        Role role = new Role();
        role.setRole(RoleType.ROLE_USER);
        if (CollectionUtils.isEmpty(roles)) {
            roles = Set.of(role);

        }
        return roles.stream()
                .map(roleMapper::toRoleType)
                .collect(Collectors.toSet());
    }
}
