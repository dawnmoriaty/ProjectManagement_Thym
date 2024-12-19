package org.example.projectmanagement.model.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @NotBlank(message = "Tên đăng nhập không được để trống")
    private String username;
    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;
}
