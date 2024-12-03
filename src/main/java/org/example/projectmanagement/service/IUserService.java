package org.example.projectmanagement.service;

import org.example.projectmanagement.model.entity.Users;

public interface IUserService {
     Users register(String username, String password);
     boolean login(String username, String password);
}
