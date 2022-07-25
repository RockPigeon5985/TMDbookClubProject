package com.endava.tmd.endavatmdbookproject.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;


@Entity(name = "authority")
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    private Long auth_id;

    @Column(name = "role")
    private String roleCode;

    @Override
    public String getAuthority() {
        return roleCode;
    }

    public Long getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(Long auth_id) {
        this.auth_id = auth_id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
