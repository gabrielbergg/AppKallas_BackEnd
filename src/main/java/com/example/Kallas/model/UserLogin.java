package com.example.Kallas.model;

import com.example.Kallas.enums.Roles;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user_login")
public class UserLogin implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String login;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Roles role;
    @Column(nullable = false)
    private String password;

    public UserLogin(String login, Roles role, String password) {
        this.login = login;
        this.role = role;
        this.password = password;
    }

    public UserLogin() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == Roles.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USERS"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USERS"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Roles getRoles() {
        return role;
    }

    public void setRoles(Roles roles) {
        this.role = roles;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLogin userLogin = (UserLogin) o;
        return Objects.equals(id, userLogin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}