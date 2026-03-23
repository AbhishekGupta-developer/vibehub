package com.myorganisation.vibehub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "profile_pictures")
public class ProfilePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String url;
    private String alternativeText;

    @OneToOne
    @JsonIgnore
    private User user;

    @OneToOne
    @JsonIgnore
    private Group group;
}
