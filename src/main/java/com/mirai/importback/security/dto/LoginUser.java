package com.mirai.importback.security.dto;

import io.cucumber.java.mk_latn.No;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class LoginUser {

    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
