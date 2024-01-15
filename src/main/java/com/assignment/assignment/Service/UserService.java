package com.assignment.assignment.Service;

import com.assignment.assignment.Dto.UserDto;
import com.assignment.assignment.Dto.UserUpdateDto;
import com.assignment.assignment.Entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User createUser(UserDto userDto);
    User updateUser(Long id, UserUpdateDto userDto);
    void deleteUser(long id);
    void uploadPassportPdf(Long id, MultipartFile file);
    void uploadUserPhoto(Long id, MultipartFile file);


}
