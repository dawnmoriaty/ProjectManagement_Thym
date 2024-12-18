package org.example.projectmanagement.service;

import org.example.projectmanagement.model.dtos.request.RegisterRequestDTO;
import org.example.projectmanagement.model.entity.Users;

import java.util.List;

public interface IUserService {
     Users registerUser(RegisterRequestDTO requestDTO);
     Users login(String username, String password);
     List<Users> getAllUsers();
}
