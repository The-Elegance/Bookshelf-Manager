package com.thelegance.bookshalf.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", schema = "public")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String username;
    String firstName;
    String lastName;
    String password;


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    Set<Role> roles = new HashSet<>();


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = false)
    Set<Grade> grades = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    Set<Book> ReadBooks = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    Set<Book> desiredBooks = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    public boolean isAccountNonExpired() {
        return false;
    }

    public boolean isAccountNonLocked() {
        return false;
    }

    public boolean isCredentialsNonExpired() {
        return false;
    }

    public boolean isEnabled() {
        return false;
    }
}
