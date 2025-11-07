package com.itvitae.projectmanagement_backend.services;

import com.itvitae.projectmanagement_backend.dto.user.UserCreateDTO;
import com.itvitae.projectmanagement_backend.dto.user.UserDTO;
import com.itvitae.projectmanagement_backend.dto.user.UserUpdateDTO;
import com.itvitae.projectmanagement_backend.dto.user.UserUpdatePasswordDTO;
import com.itvitae.projectmanagement_backend.models.User;
import com.itvitae.projectmanagement_backend.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    final private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserCreateDTO createDTO) {
        User user = createDTO.toEntity();
        User savedUser = userRepository.save(user);
        return UserDTO.fromEntity(savedUser);
    }

    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        return UserDTO.fromEntity(user);
    }

    public List<UserDTO> searchByLastName(String lastName) {
        return userRepository.findByLastnameContainingIgnoreCase(lastName)
                .stream()
                .sorted(Comparator.comparing(User::getLastName))
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public UserDTO updateUser(Long id, UserUpdateDTO updateDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        User savedUser = userRepository.save(user);
        return UserDTO.fromEntity(savedUser);
    }

    public UserDTO changePassword(Long id, String password, UserUpdatePasswordDTO updateDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        user.setPassword(password);
        User savedUser = userRepository.save(user);
        return UserDTO.fromEntity(savedUser);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
        userRepository.delete(user);
    }
}
