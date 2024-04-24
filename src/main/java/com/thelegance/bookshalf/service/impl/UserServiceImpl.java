package com.thelegance.bookshalf.service.impl;

import com.thelegance.bookshalf.model.Role;
import com.thelegance.bookshalf.model.User;
import com.thelegance.bookshalf.repository.UserRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User myUser = userRepository.findByUsername(username);

        if (myUser == null) {
            throw new UsernameNotFoundException("");
        }

        return new org.springframework.security.core.userdetails.User(myUser.getUsername(),
                myUser.getPassword(), mapRolesToAuthorities(myUser.getRoles()));
    }

    public void addUser(User user) throws Exception
    {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null)
        {
            throw new Exception("user exist");
        }
        user.setRoles(Collections.singleton(Role.DEFAULT));
        userRepository.save(user);
    }

    private List<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles)
    {
        return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" +
                r.name())).collect(Collectors.toList());
    }
}
