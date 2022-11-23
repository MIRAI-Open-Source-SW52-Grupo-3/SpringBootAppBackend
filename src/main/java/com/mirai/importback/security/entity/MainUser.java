package com.mirai.importback.security.entity;

import io.cucumber.core.cli.Main;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class MainUser implements UserDetails {

    private String name;
    private String userName;
    private String email;
    private String password;
    private String age;
    private String genre;
    private String day;
    private String month;
    private String year;
    private String phone;
    private String address;
    private Collection<? extends GrantedAuthority>authorities;

    public static MainUser build(User user){
        List<GrantedAuthority>authorityList =
                user.getRoles().stream().map(role->new SimpleGrantedAuthority(
                        role.getRoleName().name())).collect(Collectors.toList());

        return new MainUser(user.getName(), user.getUserName(), user.getEmail(),
                user.getPassword(),user.getAge(),
                user.getGenre(),user.getMonth(),user.getDay(),
                user.getAddress(),user.getPhone(),user.getYear(),authorityList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
