package com.itvitae.projectmanagement_backend.services;

import com.itvitae.projectmanagement_backend.dto.mappers.UserMapper;
import com.itvitae.projectmanagement_backend.dto.user.*;
import com.itvitae.projectmanagement_backend.exceptions.*;
import com.itvitae.projectmanagement_backend.models.User;
import com.itvitae.projectmanagement_backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    final private UserRepository userRepository;
    final private UserMapper userMapper;
    final private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserSummaryDTO createUser(UserCreateDTO dto) {
        if (userRepository.findByEmail(dto.email()).isPresent()) {
            throw new EmailAlreadyInUseException("Something went wrong. Please try again.");
        }

        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public List<UserSummaryDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserSummaryDTO getById(Long id) {
        User user = userRepository.findById(id)
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

    public UserSummaryDTO updateUser(Long id, UserUpdateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with " + id + " not found"));
        userMapper.updateFromDTO(user, dto);
        userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with " + id + " not found"));
        userRepository.delete(user);
    }

    public List<UserSummaryDTO> searchUsers(String keyword) {
        return userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                keyword, keyword, keyword
        ).stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserSummaryDTO verifyLogin(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with " + email + " not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IncorrectPasswordException("Invalid password");
        }
        return userMapper.toDTO(user);
    }

    public UserSummaryDTO changePassword(String email, String currentPassword, String newPassword) {
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
        return userMapper.toDTO(user);
    }

    public UserSummaryDTO resetPassword(String email, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            throw new PasswordsDoNotMatchException("Passwords do not match!");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with " + email + " not found"));

        if (passwordEncoder.matches(newPassword, user.getPassword())) {
            throw new SamePasswordException("New password cannot be the same as the current one.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return userMapper.toDTO(user);
    }
}
