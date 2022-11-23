package com.mirai.importback.security.dto;

import io.cucumber.java.eo.Se;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
public class NewUser {
    @NotNull
    private String name;
    @NotNull
    private String userName;
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String age;
    @NotNull
    private String genre;
    @NotNull
    private String day;
    @NotNull
    private String month;
    @NotNull
    private String year;
    @NotNull
    private String phone;
    @NotNull
    private String address;
    private Set<String> roles=new HashSet<>();
}
