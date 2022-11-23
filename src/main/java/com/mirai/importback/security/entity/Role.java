package com.mirai.importback.security.entity;

import com.mirai.importback.security.enums.RoleName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="roles")
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public Role(@NotNull RoleName roleName) {
        this.roleName = roleName;
    }
}
