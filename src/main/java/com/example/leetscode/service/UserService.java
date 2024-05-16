package com.example.leetscode.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.leetscode.model.User;
import com.example.leetscode.repository.UserRepository;

@Service
public class UserService {
    // UserService sẽ chứa các phương thức để thao tác với User
    // Ví dụ: tìm kiếm User, thêm User, xóa User, cập nhật User
    // UserService sẽ gọi các phương thức từ UserRepository để thao tác với cơ sở dữ liệu

    @Autowired // Sử dụng để tiêm UserRepository vào UserService
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    public User addUser(User user) {
        // user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void updateUser(Long judge0id, User user) {
        User userToUpdate = userRepository.findById(judge0id).get();
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setEmail(user.getEmail());
        // userToUpdate.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(userToUpdate);
    }

    
}
