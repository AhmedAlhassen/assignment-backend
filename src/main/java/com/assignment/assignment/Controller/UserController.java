package com.assignment.assignment.Controller;

import com.assignment.assignment.Dto.UserDto;
import com.assignment.assignment.Dto.UserUpdateDto;
import com.assignment.assignment.Entity.User;
import com.assignment.assignment.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDetailsDTO) {
        User createdUser = userService.createUser(userDetailsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getUser() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                                     @Valid @RequestBody UserUpdateDto userDetailsDTO) {
        User updatedUser = userService.updateUser(id, userDetailsDTO);
        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/upload-passport")
    public ResponseEntity<Void> uploadPassport(@PathVariable Long id,
                                               @RequestParam("file") MultipartFile file) {
        userService.uploadPassportPdf(id, file);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/upload-photo")
    public ResponseEntity<Void> uploadPhoto(@PathVariable Long id,
                                            @RequestParam("photo") MultipartFile file) {
        userService.uploadUserPhoto(id, file);
        return ResponseEntity.ok().build();
    }
}
