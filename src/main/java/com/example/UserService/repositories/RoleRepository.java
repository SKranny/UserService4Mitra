package com.example.UserService.repositories;


import com.example.UserService.constants.RoleType;
import com.example.UserService.models.Role;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Cacheable("roles")
    Optional<Role> findByRole(RoleType roleType);
}
