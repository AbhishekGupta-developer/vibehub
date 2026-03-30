package com.myorganisation.vibehub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "number_of_users")
@Data
public class NumberOfUser {
    @Id
    private Long id;

    private Long counter;
}
