package org.example.projectmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagement.model.dtos.request.RegisterRequestDTO;
import org.example.projectmanagement.model.entity.Users;
import org.example.projectmanagement.repository.IUsersRepository;
import org.example.projectmanagement.service.IFileService;
import org.example.projectmanagement.service.IUserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final IUsersRepository usersRepository;
    private final IFileService fileService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Users registerUser(RegisterRequestDTO requestDTO) {
        String image = fileService.uploadImage(requestDTO.getAvatar());
        Users users = Users.builder()
                .username(requestDTO.getUsername())
                .password(passwordEncoder.encode(requestDTO.getPassword()))
                .fullName(requestDTO.getFullName())
                .email(requestDTO.getEmail())
                .phone(requestDTO.getPhone())
                .address(requestDTO.getAddress())
                .IDVN(requestDTO.getIDVN())
                .avatar(image)
                .active(requestDTO.isActive())
                .build();
        return usersRepository.save(users);
    }

    @Override
    public Users login(String username, String password) {
        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username không tồn tại!"));
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            throw new RuntimeException("Mật khẩu không chính xác!");
        }
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }


}
