package com.itvitae.projectmanagement_backend.controllers;

import com.itvitae.projectmanagement_backend.dto.user.UserCreateDTO;
import com.itvitae.projectmanagement_backend.dto.user.UserDTO;
import com.itvitae.projectmanagement_backend.dto.user.UserUpdateDTO;
import com.itvitae.projectmanagement_backend.dto.user.UserUpdatePasswordDTO;
import com.itvitae.projectmanagement_backend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserCreateDTO createDTO) {
        UserDTO created = userService.createUser(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsers(@RequestParam String lastName) {
        List<UserDTO> users = userService.searchByLastName(lastName);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO updateDTO) {
        UserDTO updated = userService.updateUser(id, updateDTO);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/passwordchange/{id}/{password}")
    public ResponseEntity<UserDTO> changePassword(
            @PathVariable Long id,
            @PathVariable String password,
            @Valid @RequestBody UserUpdatePasswordDTO updateDTO) {
        UserDTO changed = userService.changePassword(id, password, updateDTO);
        return ResponseEntity.ok(changed);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
