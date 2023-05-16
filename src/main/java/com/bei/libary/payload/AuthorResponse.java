package com.bei.libary.payload;

import java.time.Instant;
import java.util.List;

import lombok.Data;


@Data
public class AuthorResponse {
    private Long id;

    private String firstName;

    private String lastName;

    private Instant dateOfBirth;

}
