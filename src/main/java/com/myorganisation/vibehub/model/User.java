package com.myorganisation.vibehub.model;

import com.myorganisation.vibehub.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String userName;
    private String password;
    private String email;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
