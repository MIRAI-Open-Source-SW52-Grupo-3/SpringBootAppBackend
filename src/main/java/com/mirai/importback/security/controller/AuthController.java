package com.mirai.importback.security.controller;


import com.mirai.importback.config.Message;
import com.mirai.importback.security.dto.JwtDTO;
import com.mirai.importback.security.dto.LoginUser;
import com.mirai.importback.security.dto.NewUser;
import com.mirai.importback.security.entity.Role;
import com.mirai.importback.security.entity.User;
import com.mirai.importback.security.enums.RoleName;
import com.mirai.importback.security.jwt.JwtProvider;
import com.mirai.importback.security.service.RoleService;
import com.mirai.importback.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {


    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/newUser")
    public ResponseEntity<?> newUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("Invalid Credentials"), HttpStatus.BAD_REQUEST);
        if(userService.existsByUserName(newUser.getUserName()))
            return new ResponseEntity(new Message("This userName exists"), HttpStatus.BAD_REQUEST);
        if (userService.existsByEmail(newUser.getEmail()))
            return new ResponseEntity(new Message("This email exists"), HttpStatus.BAD_REQUEST);

        User user =
                new User(newUser.getName(),newUser.getUserName(), newUser.getEmail(),
                        passwordEncoder.encode(newUser.getPassword()),newUser.getAge(),newUser.getGenre(),newUser.getDay(),
                        newUser.getMonth(),newUser.getYear(),newUser.getPhone(),newUser.getAddress());
        Set<Role> roles=new HashSet<>();
        roles.add(roleService.findByRoleName(RoleName.ROLE_USER).get());
        if(newUser.getRoles().contains("admin"))
            roles.add(roleService.findByRoleName(RoleName.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity(new Message("User Created"),HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<JwtDTO>login(@Valid @RequestBody LoginUser loginUser,BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("Invalid Credentials"),HttpStatus.BAD_REQUEST);
        Authentication authentication=
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUserName(),loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt= jwtProvider.generateToken(authentication);
        UserDetails userDetails=(UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO=new JwtDTO(jwt,userDetails.getUsername(),userDetails.getAuthorities());
        return new ResponseEntity(jwtDTO,HttpStatus.OK);

    }
}
