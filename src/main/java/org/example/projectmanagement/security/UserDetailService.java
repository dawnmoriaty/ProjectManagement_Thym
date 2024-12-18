package org.example.projectmanagement.security;

import lombok.AllArgsConstructor;
import org.example.projectmanagement.model.entity.Users;
import org.example.projectmanagement.repository.IUsersRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {

    private IUsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        UserPrinciple userPrinciple = new UserPrinciple();
        userPrinciple.setUsers(users);
        userPrinciple.setAuthorities(users.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName().name())) // Thêm "ROLE_" vào trước mỗi tên quyền
                .collect(Collectors.toSet()));

        return userPrinciple;
    }
}
