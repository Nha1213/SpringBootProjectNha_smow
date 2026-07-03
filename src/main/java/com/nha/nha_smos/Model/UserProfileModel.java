package com.nha.nha_smos.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "user_profile")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserProfileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
    private String address;
    private String image;

    @OneToOne()
    @JoinColumn(name = "userId")
    private UserModel user;

}
