package com.example.backend.service;

import com.example.backend.model.Users;
import com.example.backend.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UsersService {
    private final UsersRepository userRepository;

    public Users getUser(String username){
        log.info("Fetching user with username: {}", username);
        return userRepository.findById(username).get();
    }

}
