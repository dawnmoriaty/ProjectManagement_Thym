package org.example.projectmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.projectmanagement.enums.RoleUserName;
import org.example.projectmanagement.model.dtos.request.RegisterRequestDTO;
import org.example.projectmanagement.model.entity.Roles;
import org.example.projectmanagement.model.entity.Users;
import org.example.projectmanagement.repository.IRolesRepository;
import org.example.projectmanagement.repository.IUsersRepository;
import org.example.projectmanagement.service.IFileService;
import org.example.projectmanagement.service.IUserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {
    private final IUsersRepository usersRepository;
    private final IFileService fileService;
    private final PasswordEncoder passwordEncoder;
    private final IRolesRepository rolesRepository;

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
    public Users getUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Người dùng không tồn tại!"));
    }
    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
    @Override
    public void deleteUser(Long id) {
        Users user = getUserById(id);
        usersRepository.delete(user);
    }
    @Override
    public void addRoleToUser(Long id, RoleUserName roleUserName) {
        Users user = getUserById(id);
        Roles role = (Roles) rolesRepository.findByRoleName(roleUserName)
                .orElseThrow(() -> new RuntimeException("Vai trò không tồn tại!"));
        user.getRoles().add(role);
        usersRepository.save(user);
    }
}