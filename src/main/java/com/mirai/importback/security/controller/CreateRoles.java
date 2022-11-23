package com.mirai.importback.security.controller;

import com.mirai.importback.security.entity.Role;
import com.mirai.importback.security.enums.RoleName;
import com.mirai.importback.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class CreateRoles implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
       /* Role roleAdmin = new Role(RoleName.ROLE_ADMIN);
        Role roleUser= new Role(RoleName.ROLE_USER);
        roleService.save(roleAdmin);
        roleService.save(roleUser);*/
    }
}
