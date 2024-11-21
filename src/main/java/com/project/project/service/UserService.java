package com.project.project.service;

import com.project.project.domain.entity.User;
import com.project.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void register(User user){
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setUser_id(UUID.randomUUID());
        userRepository.save(user);
    }

    public boolean isEmailDuplicated(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean login(String email, String password){
        return userRepository.findByEmail(email)
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(false);
    }

    public Optional<User> authenticate(String email, String password) {
        return userRepository.findByEmail(email).filter(user -> passwordEncoder.matches(password, user.getPassword()));
    }

    // User 사용자 목록 조회
    public List<User> userList(){
        return userRepository.findAllUsers();
    }

    // User 오브젝트에서 user_id로 특정 user 삭제
    public void removeUsers(UUID user_id){
        userRepository.deleteById(user_id);
    }
}
