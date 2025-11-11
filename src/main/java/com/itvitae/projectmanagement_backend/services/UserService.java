package com.itvitae.projectmanagement_backend.services;

import com.itvitae.projectmanagement_backend.dto.mappers.UserMapper;
import com.itvitae.projectmanagement_backend.dto.user.*;
import com.itvitae.projectmanagement_backend.models.User;
import com.itvitae.projectmanagement_backend.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    final private UserRepository userRepository;
    final private UserMapper userMapper;
    final private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserSummaryDTO createUser(UserCreateDTO dto) {
        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    @Transactional
    public List<UserSummaryDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserSummaryDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        return userMapper.toDTO(user);
    }

//    public List<UserSummaryDTO> searchByLastName(String lastName) {
//        return userRepository.findByLastnameContainingIgnoreCase(lastName)
//                .stream()
//                .sorted(Comparator.comparing(User::getLastName))
//                .map(userMapper::toEntity)
//                .collect(Collectors.toList());
//    }

    @Transactional
    public UserSummaryDTO updateUser(Long id, UserUpdateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        userMapper.updateFromDTO(user, dto);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

//    public UserSummaryDTO changePassword(Long id, String password, UserUpdatePasswordDTO updateDTO) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
//        user.setPassword(password);
//        User savedUser = userRepository.save(user);
//        return UserDTO.fromEntity(savedUser);
//    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        userRepository.delete(user);
    }

    @Transactional
    public List<UserSummaryDTO> searchUsers(String keyword) {
        return userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                keyword, keyword, keyword
        ).stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public boolean verifyLogin(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
//                .orElseThrow(() -> new EntityNotFoundException("User not found with Email: " + email));

        if(optionalUser.isEmpty()) return false;

        User user = optionalUser.get();
        return passwordEncoder.matches(password, user.getPassword());
    }
}
