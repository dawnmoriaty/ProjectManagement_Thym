package org.example.projectmanagement.service;

import org.example.projectmanagement.enums.RoleUserName;
import org.example.projectmanagement.model.dtos.request.RegisterRequestDTO;
import org.example.projectmanagement.model.entity.Roles;
import org.example.projectmanagement.model.entity.Users;

import java.util.List;

public interface IUserService {
     Users registerUser(RegisterRequestDTO requestDTO);
     Users getUserById(Long id);

     List<Users> getAllUsers();
     Users updateUser (Long id, Users user);

     void deleteUser (Long id);
     void addRoleToUser (Long id, RoleUserName role);
}
