package com.cinemagic.cinemagic.security;

import java.util.ArrayList;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cinemagic.cinemagic.user.UserRepository;
import com.cinemagic.cinemagic.user.UserEntity;

import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found"));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles()
            .forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole().name())));

        user.getRoles().stream()
            .flatMap(role -> role.getPermissions().stream())
            .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(user.getEmail(), user.getPassword(), user.isActive(), user.isAccountNotExpired(), user.isAccountNotLocked(), user.isActive(), authorities);

    }
}