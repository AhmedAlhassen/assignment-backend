package com.assignment.assignment.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {
    @Id
    @Column(name = "id", length = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "name", length = 50)
    private String Name;

    @Column(name = "mobile_number", length = 50)
    private String MobileNo;

    @Column(name = "email", length = 50)
    private String EmailId;

    @Column(name = "nationality", length = 50)
    private String Nationality;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private LocalDate DateOfBirth;

//    PassportNo
    @Column(name = "passport_no")
    private String PassportNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "passport_expiry_date")
    private LocalDate PassportExpiryDate;

    @Column(name = "user_photo_path")
    private String UserPhotoPath;

    @Column(name = "passport_path")
    private String PassportPath;


}
