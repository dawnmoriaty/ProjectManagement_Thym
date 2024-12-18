package org.example.projectmanagement;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Genpass {
    public static void main(String[] args) {
        // Mật khẩu đã mã hóa
        String encodedPassword = "$2a$10$QMNM9sRZ0bcovrehgPBAIOWOKqfMUAUNoiGSyTKKhQChCkDtKLggG";

        // Kiểm tra mật khẩu với mật khẩu đã mã hóa
        boolean isPasswordMatch = checkPassword("mật khẩu bạn muốn kiểm tra", encodedPassword);
        System.out.println("Mật khẩu có hợp lệ: " + isPasswordMatch);
    }

    // Phương thức kiểm tra mật khẩu với mật khẩu đã mã hóa
    private static boolean checkPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
