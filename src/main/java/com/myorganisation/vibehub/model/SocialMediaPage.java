package com.myorganisation.vibehub.model;

import com.myorganisation.vibehub.enums.PageCategory;

import java.time.LocalDate;

public class SocialMediaPage {
    private Long id;
    private String name;
    private PageCategory category;
    private String description;
    private LocalDate createdOn;
    private String password;
}
