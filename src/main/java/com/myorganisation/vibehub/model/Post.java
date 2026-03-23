package com.myorganisation.vibehub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "posts")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caption;
    private Long likeCount;
    private Long commentCount;
    private Long shareCount;

    @ManyToOne
    @JsonIgnore
    private User user;
}
