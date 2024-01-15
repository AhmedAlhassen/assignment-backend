package com.assignment.assignment.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto implements Serializable {
    @NotEmpty
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String Name;
    @NotEmpty
    private String MobileNo;
    @NotEmpty
    @Email
    private String EmailId;
    @NotEmpty
    private String Nationality;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy/MM/dd")
    private LocalDate DateOfBirth;
    @NotEmpty
    private String PassportNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy/MM/dd")
    private LocalDate PassportExpiryDate;

}
