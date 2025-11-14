package com.itvitae.projectmanagement_backend.services;

import com.itvitae.projectmanagement_backend.dto.mappers.UserMapper;
import com.itvitae.projectmanagement_backend.dto.user.*;
import com.itvitae.projectmanagement_backend.models.User;
import com.itvitae.projectmanagement_backend.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
import com.itvitae.projectmanagement_backend.exceptions.IncorrectPasswordException;
import com.itvitae.projectmanagement_backend.exceptions.SamePasswordException;
import com.itvitae.projectmanagement_backend.exceptions.UserNotFoundException;
import com.itvitae.projectmanagement_backend.models.User;
import com.itvitae.projectmanagement_backend.repositories.UserRepository;
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

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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
                .orElseThrow(() -> new UserNotFoundException("User with " + id + " not found"));
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
                .orElseThrow(() -> new UserNotFoundException("User with " + id + " not found"));
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
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with " + id + " not found"));
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

    @Transactional
    public void verifyLogin(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with " + email + " not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IncorrectPasswordException("Invalid password");
        }
    }

    @Transactional
    public void changePassword(String email, String currentPassword, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with " + email + " not found"));

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new IncorrectPasswordException("Current password is incorrect!");
        }

        if (passwordEncoder.matches(newPassword, user.getPassword())) {
            throw new SamePasswordException("New password cannot be the same as the current one.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
