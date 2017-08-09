package com.example.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    static final long serialVersionUID = 1L;

    @Id
    @Column(name = "username", nullable = false, unique = true)
    String username;


    @OneToOne
    @JoinColumn(name = "username")
    UserRoles userRoles;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "enabled", nullable = false)
    boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String role = userRoles.getRole();

        if (role != null && !role.isEmpty()) {
            String[] roles = role.split(",");
            authorities = Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(toList());
        }

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // we never lock accounts
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // credentials never expire
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userRoles.getUsername();
    }
}
