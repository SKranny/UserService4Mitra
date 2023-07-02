package com.example.UserService.mappers;

import com.example.UserService.constants.RoleType;
import com.example.UserService.exceptions.PersonException;
import com.example.UserService.models.Role;
import com.example.UserService.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    private final RoleRepository repository;

    public Role toRole(RoleType roleType) {
        return repository.findByRole(roleType)
                .orElseThrow(() -> new PersonException("Error! Invalid role!"));
    }

    public RoleType toRoleType(Role role) {
        return role.getRole();
    }
}
