package com.itvitae.projectmanagement_backend.controllers;

import com.itvitae.projectmanagement_backend.dto.user.*;
import com.itvitae.projectmanagement_backend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {

    final private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserSummaryDTO> createUser(@Valid @RequestBody UserCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSummaryDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserSummaryDTO>> searchUsers(@RequestParam String keyword) {
        return ResponseEntity.ok(userService.searchUsers(keyword));
    }

    @GetMapping
    public ResponseEntity<List<UserSummaryDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserSummaryDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    //didnt touch this V
//    @PutMapping("/passwordchange/{id}/{password}")
//    public ResponseEntity<UserDTO> changePassword(
//            @PathVariable Long id,
//            @PathVariable String password,
//            @Valid @RequestBody UserUpdatePasswordDTO updateDTO) {
//        UserDTO changed = userService.changePassword(id, password, updateDTO);
//        return ResponseEntity.ok(changed);
//    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('REMOVE_USER')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequestDTO loginDTO) {
        userService.verifyLogin(loginDTO.email(), loginDTO.password());
        return ResponseEntity.ok("Login succesful!");
    }

    @PutMapping("/changepassword")
    public ResponseEntity<String> changePassword(@RequestBody UserChangePasswordDTO passwordDTO) {
        userService.changePassword(passwordDTO.email(), passwordDTO.currentPassword(), passwordDTO.newPassword());
        return ResponseEntity.ok("Password changed succesfully!");
    }
}
