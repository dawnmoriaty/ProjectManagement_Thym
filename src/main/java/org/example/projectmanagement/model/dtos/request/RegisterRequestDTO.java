package org.example.projectmanagement.model.dtos.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequestDTO {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String IDVN;
    private MultipartFile avatar;
    private boolean active;
}
