package com.myorganisation.vibehub.dto.response;

import lombok.Data;

@Data
public class CountryResponseDto {
    private Long id;
    private String name;
    private String slug;
    private Long code;
}
