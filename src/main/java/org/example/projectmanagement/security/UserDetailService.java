package org.example.projectmanagement.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.projectmanagement.model.entity.Users;
import org.example.projectmanagement.repository.IUsersRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailService implements UserDetailsService {
    private final IUsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        log.info("User Roles: {}", users.getRoles());
        return new org.springframework.security.core.userdetails.User(
                users.getUsername(),
                users.getPassword(),
                users.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRoleName().toString()))
                        .collect(Collectors.toList())
        );
    }
}
