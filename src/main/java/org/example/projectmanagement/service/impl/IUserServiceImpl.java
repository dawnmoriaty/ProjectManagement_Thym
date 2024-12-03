package org.example.projectmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagement.model.entity.Users;
import org.example.projectmanagement.repository.IUsersRepository;
import org.example.projectmanagement.service.IUserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IUserServiceImpl implements IUserService {
    private final IUsersRepository usersRepository;
    @Override
    public Users register(String username, String password) {
        if (usersRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username đã tồn tại!");
        }
        Users user = Users.builder()
                .username(username)
                .password(password)
                .build();
        return usersRepository.save(user);
    }

    @Override
    public boolean login(String username, String password) {
        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username không tồn tại!"));
        // Authentication logic sẽ do SecurityConfig xử lý, chỉ kiểm tra sự tồn tại
        return true;
    }
}
