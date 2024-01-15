package com.assignment.assignment.ServiceImpl;

import com.assignment.assignment.Dto.UserDto;
import com.assignment.assignment.Dto.UserUpdateDto;
import com.assignment.assignment.Entity.User;
import com.assignment.assignment.Exception.FileUploadException;
import com.assignment.assignment.Exception.NotFoundException;
import com.assignment.assignment.Repo.UserRepo;
import com.assignment.assignment.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo =userRepo;

    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepo.findAll();
    }

    @Override
    public User createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setMobileNo(userDto.getMobileNo());
        user.setEmailId(userDto.getEmailId());
        user.setNationality(userDto.getNationality());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setPassportNo(userDto.getPassportNo());
        user.setPassportExpiryDate(userDto.getPassportExpiryDate());
        return this.userRepo.save(user);
    }

    @Override
    public User updateUser(Long id , UserUpdateDto userDto) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        existingUser.setMobileNo( userDto.getMobileNo());
        existingUser.setEmailId(userDto.getEmailId());

        return this.userRepo.save(existingUser);
    }

    @Override
    public void deleteUser(long id) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        this.userRepo.delete(existingUser);
    }

    @Override
    public void uploadPassportPdf(Long id, MultipartFile file) {
        User user = this.userRepo.findById(id)
                .orElseThrow(()-> new NotFoundException("User not found with id: " + id));
        try {
            String fileName = "passport_" + id + "_" + System.currentTimeMillis() + ".pdf";
            String filePath = "D:\\projects\\Assignment/passport" + fileName; // Update the path accordingly

            // Save the file to local storage
            Path path = Paths.get(filePath);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            // Update the passportPath in UserDetails
            user.setPassportPath(filePath);
            this.userRepo.save(user);

        }catch (IOException e) {
            throw new FileUploadException("Failed to upload passport file", e);
        }

    }

    @Override
    public void uploadUserPhoto(Long id, MultipartFile file) {
        User user = this.userRepo.findById(id)
                .orElseThrow(()-> new NotFoundException("User not found with id: " + id));
        try {
            String fileName = "photo" + id + "_" + System.currentTimeMillis() + ".jpg";
            String filePath = "D:\\projects\\Assignment/photo" + fileName; // Update the path accordingly

            // Save the file to local storage
            Path path = Paths.get(filePath);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            // Update the passportPath in UserDetails
            user.setUserPhotoPath(filePath);
            this.userRepo.save(user);

        }catch (IOException e) {
            throw new FileUploadException("Failed to upload passport file", e);
        }

    }
}
