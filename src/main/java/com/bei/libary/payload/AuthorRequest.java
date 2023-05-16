package com.bei.libary.payload;


import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Data
public class AuthorRequest {
    @NotBlank
    @Size( max = 255 )
    private String firstName;

    @NotBlank
    @Size( max = 255 )
    private String lastName;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant dateOfBirth;
}
