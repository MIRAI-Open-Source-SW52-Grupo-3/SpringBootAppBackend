package com.mirai.importback.security.entity;


import io.cucumber.java.mk_latn.No;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;
    @NotNull
    @Column (unique = true)
    private String userName;
    @NotNull
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
    @NotNull
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="users_roles",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles=new HashSet<>();

    public User(@NotNull String name,@NotNull String userName,@NotNull String email,@NotNull String password,@NotNull String age,@NotNull String genre,@NotNull String day,@NotNull String month,@NotNull String year,@NotNull String phone,@NotNull String address) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.genre = genre;
        this.day = day;
        this.month = month;
        this.year = year;
        this.phone = phone;
        this.address = address;
    }
}
