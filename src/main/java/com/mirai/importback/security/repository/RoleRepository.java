package com.mirai.importback.security.repository;

import com.mirai.importback.security.entity.Role;
import com.mirai.importback.security.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByRoleName(RoleName roleName);
}
